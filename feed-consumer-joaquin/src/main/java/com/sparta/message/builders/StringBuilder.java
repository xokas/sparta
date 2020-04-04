package com.sparta.message.builders;

public class StringBuilder extends MessageBuilder<String>{
		
	public StringBuilder(byte[] array) {
		super(array);
	}
	
	@Override 
	public String construct() {
		IntegerBuilder sizeBuilder;
		Integer size;
		sizeBuilder = new IntegerBuilder(this.getArray());
		size = sizeBuilder.construct();
		this.setArray(sizeBuilder.getRemnant());
		
		return Converters.convertByteArrayToString(this.getData(size));
	}
}
