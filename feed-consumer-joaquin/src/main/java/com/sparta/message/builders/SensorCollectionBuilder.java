package com.sparta.message.builders;

import java.nio.ByteBuffer;
import com.sparta.message.objects.Sensor;
import com.sparta.message.objects.SensorCollection;

public class SensorCollectionBuilder implements MessageBuilder<SensorCollection>{

	private ByteBuffer buffer;
	
	public SensorCollectionBuilder(ByteBuffer buffer) {
		super();
		this.buffer = buffer;
	}

	@Override
	public SensorCollection construct() {
		SensorCollection result;
		Integer numberOfSensors;
		
		numberOfSensors = this.buffer.getInt();
		result = new SensorCollection(numberOfSensors);
		for(int i = 0; i < numberOfSensors; i++) {
			SensorBuilder sensorBuilder = new SensorBuilder(buffer);
			Sensor sensor = sensorBuilder.construct();		 
			result.add(sensor);
		}
		return result;
	}
}
