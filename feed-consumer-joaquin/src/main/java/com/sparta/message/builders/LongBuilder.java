package com.sparta.message.builders;

public class LongBuilder extends MessageBuilder<Long>{

	public LongBuilder(byte[] array) {
		super(array);
	}

	@Override
	public Long construct() {
		return Converters.convertByteArrayToLong(this.getData(8));
	}	
}
