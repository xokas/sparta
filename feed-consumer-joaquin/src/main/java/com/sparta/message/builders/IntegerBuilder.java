package com.sparta.message.builders;

public class IntegerBuilder extends MessageBuilder<Integer>{

	public IntegerBuilder(byte[] array) {
		super(array);
	}
	
	@Override
	public Integer construct() {
		return Converters.convertByteArrayToInt(this.getData(4));
	}

}
