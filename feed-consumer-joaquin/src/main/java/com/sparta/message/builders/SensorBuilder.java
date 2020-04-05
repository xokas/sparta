package com.sparta.message.builders;

import com.sparta.message.objects.Sensor;

public class SensorBuilder extends MessageBuilder<Sensor>{

	public SensorBuilder(byte[] array, int pointer) {
		super(array, pointer);	
	}

	@Override
	public Sensor construct() {
		Integer measure;
		String id;
		
		id = this.constructString();
		measure = this.constructInteger();
		
		return new Sensor(id, measure);
	}
}
