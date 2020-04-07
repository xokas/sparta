package com.sparta.message.objects;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class SensorCollection extends MessageObject{

	private List<Sensor> list;
	private Integer max = Integer.MIN_VALUE;
	private Integer min = Integer.MAX_VALUE;
	private Integer total = 0;
	
	public SensorCollection(int size) {
		this.list = new ArrayList<>(size);
	}	
	public void add(Sensor s) {
		this.list.add(s);
		this.total = Integer.sum(this.total, s.getMeasure());
		this.max = this.max.compareTo(s.getMeasure()) > 0 ? this.max : s.getMeasure();
		this.min =  this.min.compareTo(s.getMeasure()) < 0 ? this.min : s.getMeasure();
	}
	@Override
	public byte[] getBytes() throws IOException {
		ByteArrayOutputStream result;
		byte[] listSizeArray = ByteBuffer.allocate(4).putInt(this.list.size()).array(); 
	
		result = new ByteArrayOutputStream();
		result.write(listSizeArray);
		for(Sensor obj : list) {
			result.write(obj.getBytes());
		}
		return result.toByteArray();
	}	
	public Integer calculateAverage() {
		return Integer.divideUnsigned(this.total, this.list.size());
	}	
	public Sensor get(int i) {
		return this.list.get(i);
	}	
	public List<Sensor> getList() {
		return list;
	}
	public void setList(List<Sensor> list) {
		this.list = list;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	public Integer getMin() {
		return min;
	}
	public void setMin(Integer min) {
		this.min = min;
	}
}
