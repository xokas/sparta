package com.sparta.message.builders;

import java.util.List;
import java.util.Random;

import com.sparta.message.objects.Record;
import com.sparta.message.objects.Sensor;
import com.sparta.utils.Utils;

public class RecordBuilder extends MessageBuilder<Record>{

	public RecordBuilder(byte[] array, int pointer) {
		super(array, pointer);
	}

	@Override
	public Record construct() {
		SensorCollectionBuilder builder;
		Long recordIndex;
		Long timestamp;
		String city;
		Integer numberBytesSensorData;
		List<Sensor> sensorsData;
		Long crc32SensorsData;
		
		recordIndex = this.constructLong();
		timestamp = this.constructLong();
		city = this.constructString();
		numberBytesSensorData = this.constructInteger();
		
		builder = new SensorCollectionBuilder(this.getArray(), this.getPointer());
		sensorsData = (List<Sensor>) builder.construct();
		this.setPointer(builder.getPointer());
		
		crc32SensorsData = this.constructLong();
		
		return new Record(recordIndex, timestamp, city, numberBytesSensorData, sensorsData, crc32SensorsData);
	}

	public static Record constructTestData(Random ran) {
		Long index = LongBuilder.constructTestData(ran);
		Long timestamp = LongBuilder.constructTestData(ran);
		String city = StringBuilder.constructTestData(ran);
	    List<Sensor> sensors = SensorCollectionBuilder.constructTestData(1, ran);
	    Integer numberBytesSensorData =  Integer.valueOf(SensorCollectionBuilder.constructMessage(sensors).length);
	    Long crc32SensorsData = LongBuilder.constructTestData(ran);
		
		return new Record(index, timestamp, city, numberBytesSensorData, sensors, crc32SensorsData);
	}

	public static byte[] constructMessage(Record obj) {
		byte[] result;
		
		result = LongBuilder.constructMessage(obj.getIndex());
		result = Utils.mergeArrays(result, LongBuilder.constructMessage(obj.getTimestamp()));
		result = Utils.mergeArrays(result, StringBuilder.constructMessage(obj.getCity()));
		result = Utils.mergeArrays(result, IntegerBuilder.constructMessage(obj.getNumberBytesSensorData()));
		result = Utils.mergeArrays(result, SensorCollectionBuilder.constructMessage(obj.getSensors()));
		result = Utils.mergeArrays(result, LongBuilder.constructMessage(obj.getCrc32SensorsData()));
		
		return result;
	}
	
}
