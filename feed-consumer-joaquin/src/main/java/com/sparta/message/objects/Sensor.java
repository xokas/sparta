package com.sparta.message.objects;

public class Sensor {

	private String id;
	private Integer measure;
	
	public Sensor(String id, Integer measure) {
		this.id = id;
		this.measure = measure;
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
