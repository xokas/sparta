package com.sparta.message.objects;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Sensor extends MessageObject{

	private String id;
	private Integer measure;
	
	public Sensor(String id, Integer measure) {
		this.id = id;
		this.measure = measure;
	}
	@Override
	public byte[] getBytes() throws IOException {
		ByteArrayOutputStream result;
		int resultSize = 0;
		byte[] idByteArray = this.id.getBytes();
		byte[] idSizeByteArray = ByteBuffer.allocate(4).putInt(idByteArray.length).array();
		byte[] measureArray = ByteBuffer.allocate(4).putInt(this.measure).array();
		
		resultSize = idSizeByteArray.length + idByteArray.length + measureArray.length;
		result = new ByteArrayOutputStream(resultSize);
		result.write(idSizeByteArray);
		result.write(idByteArray);
		result.write(measureArray);
		
		return result.toByteArray();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMeasure() {
		return measure;
	}
	public void setMeasure(Integer measure) {
		this.measure = measure;
	}
}
