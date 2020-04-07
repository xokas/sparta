package com.sparta.message.builders;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import com.sparta.message.objects.Record;

public class LoadBatchBuilder implements MessageBuilder<List<Record>> {

	protected ByteBuffer buffer;
		
	public LoadBatchBuilder(byte[] array) {
		super();
		this.buffer = ByteBuffer.wrap(array);
	}

	@Override
	public List<Record> construct() {
		List<Record> result;
		Long numberOfRecords;
		
		numberOfRecords = this.buffer.getLong();
		result = new ArrayList<>(numberOfRecords.intValue());
		
		for(int i = 0; i < numberOfRecords; i++) {
			RecordBuilder builder = new RecordBuilder(buffer);
			Record sensor = builder.construct();
			result.add(sensor);
		}
		
		return result;
	}
}
