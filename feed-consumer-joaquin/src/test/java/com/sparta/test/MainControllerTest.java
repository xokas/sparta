package com.sparta.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sparta.SpartaApplication;
import com.sparta.controller.MainController;
import com.sparta.message.builders.LoadBatchBuilder;
import com.sparta.message.objects.Record;

//@SpringBootTest
@SpringBootTest(classes={SpartaApplication.class})
public class MainControllerTest {

	private static Logger log = LogManager.getLogger(MainControllerTest.class);
	
	@Autowired
	MainController mainController;
	
	@Test
	void indexShouldWork() {
		assertNotNull(this.mainController.index());
	}
	
	@Test
	void loadBatchBuilderShouldWork() {
		int totalRecord = 1;
		int totalSensor = 1;
		List<Record> listTestData = new ArrayList<>(1);
		List<Record> listMessage = new ArrayList<>(1);
		byte[] content;
		LoadBatchBuilder batchBuilder;
		MessageTestDataBuilder dataBuilder;
		try {
			dataBuilder = new MessageTestDataBuilder(totalRecord, totalSensor);
			listTestData = dataBuilder.construct();
			content = buildMessage(listTestData);
			batchBuilder = new LoadBatchBuilder(content);
			listMessage = batchBuilder.construct();
		}catch(Exception e) {
			log.error(e);
		}
		
		assertNotNull(listTestData);
		assertNotNull(listMessage);
		assertEquals(listTestData.get(0).getCity(), listMessage.get(0).getCity(), "city");
		assertEquals(listTestData.get(0).getIndex(), listMessage.get(0).getIndex(), "index");
		assertEquals(listTestData.get(0).getTimestamp(), listMessage.get(0).getTimestamp(), "timestamp");
		assertEquals(listTestData.get(0).getNumberBytesSensorData(), listMessage.get(0).getNumberBytesSensorData(), "numberBytesSensor");
		assertEquals(listTestData.get(0).getCrc32SensorsData(), listMessage.get(0).getCrc32SensorsData(), "crc2sensorsData");
		assertEquals(listTestData.get(0).getSensors().get(0).getId(), listMessage.get(0).getSensors().get(0).getId(), "sensors.id");
		assertEquals(listTestData.get(0).getSensors().get(0).getMeasure(), listMessage.get(0).getSensors().get(0).getMeasure(), "sensors.measure");
	}
	
	@Test
	void loadShouldWork() {
		int totalRecord = 1;
		int totalSensor = 1;
		int result;
		String provider;
		List<Record> list;
		byte[] message;
		MessageTestDataBuilder builder;
		
		try {
			provider = "testProvider";
			builder = new MessageTestDataBuilder(totalRecord, totalSensor);
			list = builder.construct();
			message = buildMessage(list);
			result = this.mainController.load(provider, message);
		}catch(Exception e) {
  			log.error(e);
			result = -1;
		}
		
		assertEquals(result, totalRecord);
	}
	
	@Test
	void loadErrorShouldWork() {
		int result;
		String provider;
		byte[] content;
		Long message = Long.valueOf(5);
		try {
			provider = "testProvider";
			content = ByteBuffer.allocate(8).putLong(message.intValue()).array();
			result = this.mainController.load(provider, content);
		}catch(Exception e) {
  			log.error(e);
			result = 0;
		}
		
		assertEquals(result, MainController.REST_ERROR);
	}
	
	@Test
	void totalShouldWork() {
		int totalRecord = 1;
		int totalSensor = 1;
		int result;
		String provider;
		List<Record> list;
		byte[] message;
		MessageTestDataBuilder builder;
		
		try {
			provider = "testProvider";
			builder = new MessageTestDataBuilder(totalRecord, totalSensor);
			list = builder.construct();
			message = buildMessage(list);
			this.mainController.load(provider, message);
			result = this.mainController.total(provider);
		}catch(Exception e) {
  			log.error(e);
			result = -1;
		}
		
		assertEquals(result, totalRecord);
	}
	
	@Test
	void totalErrorShouldWork() {
		int result;
		String provider;
		
		try {
			provider = "fakeProvider";
			result = this.mainController.total(provider);
		}catch(Exception e) {
  			log.error(e);
			result = -1;
		}
		
		assertEquals(result, MainController.REST_ERROR);
	}
	
	private static byte[] buildMessage(List<Record> list) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		result.write(ByteBuffer.allocate(8).putLong(Long.valueOf(list.size())).array());

		for(Record obj : list) {
			result.write(obj.getBytes());
		}
		
		return result.toByteArray();
	}
	
}
