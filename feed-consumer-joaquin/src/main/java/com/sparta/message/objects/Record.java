package com.sparta.message.objects;

import java.util.List;

public class Record {

	private Long index;
	private Long timestamp;
	private String city;
	private Integer numberBytesSensorData;
	private List<Sensor> sensors;
	private Long crc32SensorsData;
	
	public Record(Long index, Long timestamp, String city, Integer numberBytesSensorData, List<Sensor> sensors,
			Long crc32SensorsData) {
		super();
		this.index = index;
		this.timestamp = timestamp;
		this.city = city;
		this.numberBytesSensorData = numberBytesSensorData;
		this.sensors = sensors;
		this.crc32SensorsData = crc32SensorsData;
	}
	public Long getIndex() {
		return index;
	}
	public void setIndex(Long index) {
		this.index = index;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<Sensor> getSensors() {
		return sensors;
	}
	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}
	public Long getCrc32SensorsData() {
		return crc32SensorsData;
	}
	public void setCrc32SensorsData(Long crc32SensorsData) {
		this.crc32SensorsData = crc32SensorsData;
	}
	public Integer getNumberBytesSensorData() {
		return numberBytesSensorData;
	}
	public void setNumberBytesSensorData(Integer numberBytesSensorData) {
		this.numberBytesSensorData = numberBytesSensorData;
	}
}
