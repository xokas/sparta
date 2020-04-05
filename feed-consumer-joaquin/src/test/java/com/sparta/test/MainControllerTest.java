package com.sparta.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sparta.controller.MainController;
import com.sparta.message.builders.LoadBatchBuilder;
import com.sparta.message.objects.Record;
import com.sparta.utils.Converters;

@SpringBootTest
public class MainControllerTest {

	static Logger log = LogManager.getLogger(MainController.class);
	
	@Autowired
	MainController mainController;
	
	@Test
	void indexShouldWork() {
		assertNotNull(this.mainController.index());
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
