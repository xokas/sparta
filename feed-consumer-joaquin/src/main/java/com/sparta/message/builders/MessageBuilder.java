package com.sparta.message.builders;

import java.util.Arrays;

public abstract class MessageBuilder<T>{

	private byte[] array;
	private byte[] data;
	private int size;
	private int pointer;
	
	public MessageBuilder(byte[] array, int pointer) {
		this.array = array;
		this.pointer = pointer;
	}
	
	public abstract T construct();
	
	protected byte[] getArray() {
		return this.array;
	}
	
	protected void setArray(byte[] array) {
		this.array = array;
	}
	
	protected byte[] getData(int size) {
		if(this.data == null) {
			this.size = size;
			this.data = Arrays.copyOfRange(this.array, this.pointer, movePointer());
		}
		return this.data;
	}
	
	private int movePointer() {
		this.pointer += this.size;
		return this.pointer;
	}
	
	protected int getSize() {
		return this.size;
	}
	
	public int getPointer() {
		return this.pointer;
	}
	
	protected void setPointer(int pointer) {
		this.pointer = pointer;
	}
	
	protected Long constructLong() {
		Long result;
		
		LongBuilder builder = new LongBuilder(this.getArray(), this.getPointer());
		result = builder.construct();
		this.setPointer(builder.getPointer());
		
		return result;
	}	
	
	protected String constructString() {
		String result;
		
		StringBuilder builder = new StringBuilder(this.getArray(), this.getPointer());
		result = builder.construct();
		this.setPointer(builder.getPointer());
		
		return result;
	}
	
	protected Integer constructInteger() {
		Integer result;
		
		IntegerBuilder builder = new IntegerBuilder(this.getArray(), this.getPointer());
		result = builder.construct();
		this.setPointer(builder.getPointer());
		
		return result;
	}
}
