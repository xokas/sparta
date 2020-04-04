package com.sparta.message.builders;

import java.util.ArrayList;
import java.util.List;

import com.sparta.message.objects.Record;

public class LoadBatchBuilder extends MessageBuilder<List<Record>> {

	public LoadBatchBuilder(byte[] array) {
		super(array);
	}

	@Override
	public List<Record> construct() {
		List<Record> result;
		LongBuilder numberOfRecordsBuilder;
		Long numberOfRecords;
		
		numberOfRecordsBuilder = new LongBuilder(this.getArray());
		numberOfRecords = numberOfRecordsBuilder.construct();
		result = new ArrayList<>(numberOfRecords.intValue());
		this.setRemnant(numberOfRecordsBuilder.getRemnant());
		for(int i = 0; i < numberOfRecords; i++) {
			RecordBuilder recordBuilder = new RecordBuilder(this.getRemnant());
			Record sensor = recordBuilder.construct();
			this.setRemnant(recordBuilder.getRemnant());
			result.add(sensor);
		}
		return result;
	}

}
