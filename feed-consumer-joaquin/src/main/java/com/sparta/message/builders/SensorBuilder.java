package com.sparta.message.builders;

import java.nio.ByteBuffer;
import com.sparta.message.objects.Sensor;

public class SensorBuilder implements MessageBuilder<Sensor>{

	private ByteBuffer buffer;
	
	public SensorBuilder(ByteBuffer buffer) {
		super();
		this.buffer = buffer;
	}

	@Override
	public Sensor construct() {
		Integer measure;
		String id;
		byte[] idArray;
		int idSize;
		
		idSize = this.buffer.getInt();
		idArray = new byte[idSize];
		this.buffer.get(idArray);
		id = new String(idArray);
		measure = this.buffer.getInt();
		
		return new Sensor(id, measure);
	}
}
