package com.sparta.message.builders;

import java.util.Random;

import com.sparta.message.objects.Sensor;
import com.sparta.utils.Converters;
import com.sparta.utils.Utils;

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

	public static Sensor constructTestData(Random ran) {
		String id = StringBuilder.constructTestData(ran);
		Integer measure = IntegerBuilder.constructTestData(ran);
		return new Sensor(id, measure);
	}

	public static byte[] constructMessage(Sensor obj) {
		byte[] result;
		byte[] idArray;
		byte[] measureArray;
		
		idArray = StringBuilder.constructMessage(obj.getId());
		measureArray = IntegerBuilder.constructMessage(obj.getMeasure());
		result = Utils.mergeArrays(idArray, measureArray);
			
		return result;
	}
}
