package com.sparta.message.builders;

import com.sparta.message.objects.Sensor;

public class SensorBuilder extends MessageBuilder<Sensor>{

	public SensorBuilder(byte[] array) {
		super(array);	
	}

	@Override
	public Sensor construct() {
		IntegerBuilder measureBuilder;
		Integer measure;
		StringBuilder idBuilder;
		String id;
		
		idBuilder = new StringBuilder(this.getArray());
		id = idBuilder.construct();
		
		measureBuilder = new IntegerBuilder(idBuilder.getRemnant());
		measure = measureBuilder.construct();
		this.setRemnant(measureBuilder.getRemnant());
		
		return new Sensor(id, measure);
	}
}
