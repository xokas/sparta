package com.sparta.message.objects;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Record extends MessageObject{

	private Long index;
	private Long timestamp;
	private String city;
	private Integer numberBytesSensorData;
	private SensorCollection sensors;
	private Long crc32SensorsData;
	
	public Record(Long index, Long timestamp, String city, Integer numberBytesSensorData, SensorCollection sensors, Long crc32SensorsData) {
		super();
		this.index = index;
		this.timestamp = timestamp;
		this.city = city;
		this.numberBytesSensorData = numberBytesSensorData;
		this.sensors = sensors;
		this.crc32SensorsData = crc32SensorsData;
	}
	protected byte[] toByteArray() throws IOException {
		ByteArrayOutputStream result;
		int resultSize = 0;
		byte[] indexArray = ByteBuffer.allocate(8).putLong(this.index).array();
		byte[] timestampArray = ByteBuffer.allocate(8).putLong(this.timestamp).array();
		byte[] cityByteArray = this.city.getBytes();
		byte[] citySizeByteArray = ByteBuffer.allocate(4).putInt(cityByteArray.length).array();
		byte[] numberBytesSensorDataArray = ByteBuffer.allocate(4).putInt(this.numberBytesSensorData).array();
		byte[] sensorsArray = this.sensors.toByteArray();
		byte[] crc32SensorsDataArray = ByteBuffer.allocate(8).putLong(this.crc32SensorsData).array();
		
		resultSize = indexArray.length
						+ timestampArray.length
						+ citySizeByteArray.length
						+ cityByteArray.length
						+ numberBytesSensorDataArray.length
						+ sensorsArray.length
						+ crc32SensorsDataArray.length;
		
		result = new ByteArrayOutputStream(resultSize);
		result.write(indexArray);
		result.write(timestampArray);
		result.write(citySizeByteArray);
		result.write(cityByteArray);
		result.write(numberBytesSensorDataArray);
		result.write(sensorsArray);
		result.write(crc32SensorsDataArray);
		
		return result.toByteArray();
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
	public SensorCollection getSensors() {
		return sensors;
	}
	public void setSensors(SensorCollection sensors) {
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
