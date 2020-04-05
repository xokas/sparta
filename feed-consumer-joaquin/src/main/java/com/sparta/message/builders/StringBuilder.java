package com.sparta.message.builders;

public class StringBuilder extends MessageBuilder<String>{
		
	public StringBuilder(byte[] array, int pointer) {
		super(array, pointer);
	}
	
	@Override 
	public String construct() {
		MessageBuilder<?> builder;
		Integer size;
		builder = new IntegerBuilder(this.getArray(), this.getPointer());
		size = (Integer) builder.construct();
		this.setPointer(builder.getPointer());
		
		return Converters.convertByteArrayToString(this.getData(size));
	}
}
