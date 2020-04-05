package com.sparta.message.builders;

import java.util.Random;

import com.sparta.utils.Converters;

public class LongBuilder extends MessageBuilder<Long>{

	public LongBuilder(byte[] array, int pointer) {
		super(array, pointer);
	}

	@Override
	public Long construct() {
		return Converters.convertByteArrayToLong(this.getData(8));
	}

	public static Long constructTestData(Random ran) {
		return ran.nextLong();
	}

	public static byte[] constructMessage(Long number) {
		return Converters.convertLongToByteArray(number);
	}	
}
