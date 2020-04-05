package com.sparta.message.builders;

import java.util.Random;

import com.sparta.utils.Converters;

public class IntegerBuilder extends MessageBuilder<Integer>{

	public IntegerBuilder(byte[] array, int pointer) {
		super(array, pointer);
	}
	
	@Override
	public Integer construct() {
		return Converters.convertByteArrayToInt(this.getData(4));
	}

	public static Integer constructTestData(Random ran) {
		return ran.nextInt();
	}

	public static byte[] constructMessage(int number) {
		return Converters.convertIntToByteArray(number);
	}

}
