package com.sparta.message.builders;

import java.nio.ByteBuffer;
import com.sparta.message.objects.Record;
import com.sparta.message.objects.SensorCollection;

public class RecordBuilder implements MessageBuilder<Record>{

	private ByteBuffer buffer;
	
	public RecordBuilder(ByteBuffer buffer) {
		super();
		this.buffer = buffer;
	}

	@Override
	public Record construct() {
		Long recordIndex;
		Long timestamp;
		Integer citySize;
		byte[] cityByteArray;
		String city;
		Integer numberBytesSensorData;
		SensorCollection sensorsData;
		Long crc32SensorsData;
		SensorCollectionBuilder sensorCollectionBuilder;
		
		recordIndex = this.buffer.getLong();
		timestamp = this.buffer.getLong();
		citySize = this.buffer.getInt();
		cityByteArray = new byte[citySize];
		this.buffer.get(cityByteArray);
		city = new String(cityByteArray);
		numberBytesSensorData = this.buffer.getInt();
		sensorCollectionBuilder = new SensorCollectionBuilder(buffer);
		sensorsData = sensorCollectionBuilder.construct();
		crc32SensorsData = buffer.getLong();
		
		return new Record(recordIndex, timestamp, city, numberBytesSensorData, sensorsData, crc32SensorsData);
	}	
}
