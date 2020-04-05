package com.sparta.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sparta.dao.intfc.RecordRODao;
import com.sparta.database.MemoryDatabase;
import com.sparta.message.objects.Record;

@Component
public class RecordMemRODao implements RecordRODao{

	@Autowired
	private MemoryDatabase database;

	@Override
	public int getTotalByProvider(String provider) {
		int result = -1;
		List<Record> records = this.database.getRecords().get(provider);
		if(!CollectionUtils.isEmpty(records)) {
			result = records.size();
		}
		
		return result;
	}
}
