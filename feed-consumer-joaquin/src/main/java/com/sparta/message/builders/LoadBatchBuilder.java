package com.sparta.message.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sparta.message.objects.Record;
import com.sparta.utils.Utils;

public class LoadBatchBuilder extends MessageBuilder<List<Record>> {

	public LoadBatchBuilder(byte[] array) {
		super(array, 0);
	}

	@Override
	public List<Record> construct() {
		List<Record> result;
		Long numberOfRecords;
		
		numberOfRecords = this.constructLong();
		result = this.constructListRecord(numberOfRecords);
		
		return result;
	}

	private List<Record> constructListRecord(Long numberOfRecords) {
		List<Record> result = new ArrayList<>(numberOfRecords.intValue());
		for(int i = 0; i < numberOfRecords; i++) {
			RecordBuilder builder = new RecordBuilder(this.getArray(), this.getPointer());
			Record sensor = builder.construct();
			this.setPointer(builder.getPointer());
			result.add(sensor);
		}
		
		return result;
	}

	public static List<Record> constructTestData(int size) {
		List<Record> list = new ArrayList<>(size);
		Random ran = new Random();
		
		for(int i = 0; i < size; i++) {
			Record record = RecordBuilder.constructTestData(ran);
			list.add(record);
		}
		
		return list;
	}

	public static byte[] constructMessage(List<Record> list) {
		byte[] result;
		byte[] recordsArray;
		
		result = LongBuilder.constructMessage(Long.valueOf(list.size()));
		for(Record obj : list) {
			recordsArray = RecordBuilder.constructMessage(obj);
			result = Utils.mergeArrays(result, recordsArray);	
		}
		
		return result;
	}

}
