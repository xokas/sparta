package com.sparta.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sparta.dao.intfc.RecordDao;
import com.sparta.database.MemoryDatabase;
import com.sparta.message.objects.Record;

@Component
public class RecordMemDao implements RecordDao {
	
	@Autowired
	private MemoryDatabase database;
	
	@Override
	public int save(String provider, List<Record> list) {
		int result = -1;
		
		this.database.getRecords().put(provider, list);
		result = list.size();
		
		return result;
	}

}
