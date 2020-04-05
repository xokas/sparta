package com.sparta.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import com.sparta.utils.Converters;

//@SpringBootTest
@SpringBootTest(classes={SpartaApplication.class})
public class MainControllerTest {

	static Logger log = LogManager.getLogger(MainController.class);
	
	@Autowired
	MainController mainController;
	
	@Test
	void indexShouldWork() {
		assertNotNull(this.mainController.index());
	}
	
	@Test
	void loadBatchBuilderShouldWork() {
		int total = 1;
		List<Record> listTestData = null;
		List<Record> listMessage = null;
		byte[] content;
		LoadBatchBuilder builder;
		
		try {
			listTestData = LoadBatchBuilder.constructTestData(total);
			content = LoadBatchBuilder.constructMessage(listTestData);
			builder = new LoadBatchBuilder(content);
			listMessage = builder.construct();
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
		int total = 1;
		int result;
		String provider;
		List<Record> list;
		byte[] content;
		
		try {
			provider = "testProvider";
			list = LoadBatchBuilder.constructTestData(total);
			content = LoadBatchBuilder.constructMessage(list);
			result = this.mainController.load(provider, content);
		}catch(Exception e) {
  			log.error(e);
			result = -1;
		}
		
		assertEquals(result, total);
	}
	
	@Test
	void loadErrorShouldWork() {
		int result;
		String provider;
		byte[] content;
		
		try {
			provider = "testProvider";
			content = provider.getBytes();
			result = this.mainController.load(provider, content);
		}catch(Exception e) {
  			log.error(e);
			result = -1;
		}
		
		assertEquals(result, MainController.REST_ERROR);
	}
	
	@Test
	void totalShouldWork() {
		int total = 1;
		int result;
		String provider;
		List<Record> list;
		byte[] content;
		
		try {
			provider = "testProvider";
			list = LoadBatchBuilder.constructTestData(total);
			content = LoadBatchBuilder.constructMessage(list);
			this.mainController.load(provider, content);
			result = this.mainController.total(provider);
		}catch(Exception e) {
  			log.error(e);
			result = -1;
		}
		
		assertEquals(result, total);
	}
	
	@Test
	void totalErrorShouldWork() {
		int result;
		String provider;
		
		try {
			provider = "aaaa";
			result = this.mainController.total(provider);
		}catch(Exception e) {
  			log.error(e);
			result = -1;
		}
		
		assertEquals(result, MainController.REST_ERROR);
	}
	
	@Test
	void testWork() {
		String hola = "hola";
		String holaArrayConvert = Converters.convertByteArrayToString(hola.getBytes());
		
		assertEquals(hola, holaArrayConvert);
		
	}
	
}
