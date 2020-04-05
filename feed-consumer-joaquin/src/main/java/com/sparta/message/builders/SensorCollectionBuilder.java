package com.sparta.message.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sparta.message.objects.Sensor;
import com.sparta.utils.Converters;
import com.sparta.utils.Utils;

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

	public static List<Sensor> constructTestData(int size, Random ran) {
		List<Sensor> list = new ArrayList<>(size);
		
		for(int i = 0; i < size; i++) {
			Sensor record = SensorBuilder.constructTestData(ran);
			list.add(record);
		}
		
		return list;
	}

	public static byte[] constructMessage(List<Sensor> list) {
		byte[] result;
		byte[] sensorsArray;
		
		Integer numberOfSensors = list.size();
		result = Converters.convertIntToByteArray(numberOfSensors);
		
		for(Sensor obj : list) {
			sensorsArray = SensorBuilder.constructMessage(obj);
			result = Utils.mergeArrays(result, sensorsArray);
		}
		
		return result;
	}	
}
