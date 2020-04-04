package com.sparta.message.builders;

import java.util.List;

import com.sparta.message.objects.Record;
import com.sparta.message.objects.Sensor;

public class RecordBuilder extends MessageBuilder<Record>{

	public RecordBuilder(byte[] array) {
		super(array);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Record construct() {
		MessageBuilder<?> builder;
		Long recordIndex;
		Long timestamp;
		String city;
		Integer numberBytesSensorData;
		List<Sensor> sensorsData;
		Long crc32SensorsData;
		
		builder = new LongBuilder(this.getArray());
		recordIndex = (Long) builder.construct();
		
		builder = new LongBuilder(builder.getRemnant());
		timestamp = (Long) builder.construct();
		
		builder = new StringBuilder(builder.getRemnant());
		city = (String) builder.construct();
		
		builder = new IntegerBuilder(builder.getRemnant());
		numberBytesSensorData = (Integer) builder.construct();
		
		builder = new SensorCollectionBuilder(builder.getRemnant());
		sensorsData = (List<Sensor>) builder.construct();
		
		builder = new LongBuilder(builder.getRemnant());
		crc32SensorsData = (Long) builder.construct();
		
		this.setRemnant(builder.getRemnant());
		
		return new Record(recordIndex, timestamp, city, numberBytesSensorData, sensorsData, crc32SensorsData);
	}

	
}
