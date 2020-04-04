package com.sparta.message.builders;

import java.util.Arrays;

public abstract class MessageBuilder<T>{

	private byte[] array;
	private byte[] data;
	private int size;
	private byte[] remnant;
	
	public MessageBuilder(byte[] array) {
		this.array = array;
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
			this.data = Arrays.copyOfRange(this.array, 0, this.size);
		}
		return this.data;
	}
	
	public byte[] getRemnant() {
		if(this.remnant == null) {
			this.remnant = Arrays.copyOfRange(this.array, this.size, this.array.length);
		}
		return this.remnant;
	}
	
	protected void setRemnant(byte[] remnant) {
		this.remnant = remnant;
	}	
	
	protected int getSize() {
		return this.size;
	}
}
