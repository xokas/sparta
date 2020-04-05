package com.sparta.message.builders;

import java.util.ArrayList;
import java.util.List;

import com.sparta.message.objects.Record;

public class LoadBatchBuilder extends MessageBuilder<List<Record>> {

	public LoadBatchBuilder(byte[] array) {
		super(array, 0);
	}

	@Override
	public List<Record> construct() {
		List<Record> result;
		Long numberOfRecords;
		
		numberOfRecords = this.constructLong();
		result = new ArrayList<>(numberOfRecords.intValue());
		for(int i = 0; i < numberOfRecords; i++) {
			RecordBuilder builder = new RecordBuilder(this.getArray(), this.getPointer());
			Record sensor = builder.construct();
			this.setPointer(builder.getPointer());
			result.add(sensor);
		}
		return result;
	}

}
