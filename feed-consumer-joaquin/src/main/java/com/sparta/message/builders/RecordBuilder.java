package com.sparta.message.builders;

import java.util.List;

import com.sparta.message.objects.Record;
import com.sparta.message.objects.Sensor;

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
	
}
