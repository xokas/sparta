package com.sparta.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sparta.dao.intfc.RecordRODao;
import com.sparta.database.MemoryDatabase;

@Component
public class RecordMemRODao implements RecordRODao{

	@Autowired
	private MemoryDatabase database;

	@Override
	public int getTotalByProvider(String provider) {
		int result = -1;
		
		result = this.database.getRecords().get(provider).size();
		
		return result;
	}
}
