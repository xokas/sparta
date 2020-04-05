package com.sparta.message.builders;

import java.util.ArrayList;
import java.util.List;

import com.sparta.message.objects.Sensor;

public class SensorCollectionBuilder extends MessageBuilder<List<Sensor>>{

	public SensorCollectionBuilder(byte[] array, int pointer) {
		super(array, pointer);	
	}

	@Override
	public List<Sensor> construct() {
		List<Sensor> result;
		Integer numberOfSensors;
		
		numberOfSensors = this.constructInteger();
		result = new ArrayList<>(numberOfSensors);
		for(int i = 0; i < numberOfSensors; i++) {
			SensorBuilder sensorBuilder = new SensorBuilder(this.getArray(), this.getPointer());
			Sensor sensor = sensorBuilder.construct();
			this.setPointer(sensorBuilder.getPointer());
			result.add(sensor);
		}
		
		return result;
	}	
}
