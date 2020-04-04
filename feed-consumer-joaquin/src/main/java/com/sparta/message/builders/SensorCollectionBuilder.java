package com.sparta.message.builders;

import java.util.ArrayList;
import java.util.List;

import com.sparta.message.objects.Sensor;

public class SensorCollectionBuilder extends MessageBuilder<List<Sensor>>{

	public SensorCollectionBuilder(byte[] array) {
		super(array);	
	}

	@Override
	public List<Sensor> construct() {
		List<Sensor> result;
		IntegerBuilder numberOfSensorsBuilder;
		Integer numberOfSensors;
		
		numberOfSensorsBuilder = new IntegerBuilder(this.getArray());
		numberOfSensors = numberOfSensorsBuilder.construct();
		result = new ArrayList<>(numberOfSensors);
		this.setRemnant(numberOfSensorsBuilder.getRemnant());
		for(int i = 0; i < numberOfSensors; i++) {
			SensorBuilder sensorBuilder = new SensorBuilder(this.getRemnant());
			Sensor sensor = sensorBuilder.construct();
			this.setRemnant(sensorBuilder.getRemnant());
			result.add(sensor);
		}
		
		return result;
	}	
}
