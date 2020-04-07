package com.sparta.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sparta.message.builders.MessageBuilder;
import com.sparta.message.objects.Record;
import com.sparta.message.objects.Sensor;
import com.sparta.message.objects.SensorCollection;

public class MessageTestDataBuilder implements MessageBuilder<List<Record>>{

	private static Logger log = LogManager.getLogger(MessageTestDataBuilder.class);
	
	private int sizeRecordList;
	private int sizeSensorCollection;
	
	public MessageTestDataBuilder(int sizeRecordList, int sizeSensorCollection) {
		this.sizeRecordList = sizeRecordList;
		this.sizeSensorCollection = sizeSensorCollection;
	}
	
	@Override
	public List<Record> construct() {
		List<Record> result;
		try {
			return constructListRecordTestData(this.sizeRecordList, this.sizeSensorCollection);
		}catch(Exception e) {
			log.error("Error al construir los datos de prueba");
			result = new ArrayList<>();
		}
		return result;
	}

	public static List<Record> constructListRecordTestData(int sizeRecordList, int sizeSensorCollection) throws IOException {
		List<Record> list = new ArrayList<>(sizeRecordList);
		Random ran = new Random();
		
		for(int i = 0; i < sizeRecordList; i++) {
			Record record = constructRecordTestData(ran, sizeSensorCollection);
			list.add(record);
		}
		
		return list;
	}
	
	public static Record constructRecordTestData(Random ran, int sizeSensorCollection) throws IOException {
		Long index = ran.nextLong();
		Long timestamp = ran.nextLong();
		byte[] array = new byte[7];
	    new Random().nextBytes(array);
		String city = new String(array);
	    SensorCollection sensors = constructSensorCollectionTestData(ran, sizeSensorCollection);
	    Integer numberBytesSensorData =  Integer.valueOf(sensors.getBytes().length);
	    Long crc32SensorsData = ran.nextLong();
		
		return new Record(index, timestamp, city, numberBytesSensorData, sensors, crc32SensorsData);
	}

	public static SensorCollection constructSensorCollectionTestData(Random ran, int sizeSensorCollection) {
		SensorCollection sensors = new SensorCollection(sizeSensorCollection);

		for(int i = 0; i < sizeSensorCollection; i++) {
			Sensor sensor = constructSensorTestData(ran);
			sensors.add(sensor);
		}
		
		return sensors;
	}
	
	public static Sensor constructSensorTestData(Random ran) {
		byte[] array = new byte[7];
	    new Random().nextBytes(array);
		String id = new String(array);
		Integer measure = ran.nextInt();
		return new Sensor(id, measure);
	}
}
