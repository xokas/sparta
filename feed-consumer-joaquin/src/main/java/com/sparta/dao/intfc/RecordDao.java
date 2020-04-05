package com.sparta.dao.intfc;

import java.util.List;

import com.sparta.message.objects.Record;

public interface RecordDao {

	public int save(String provider, List<Record> list);
	
}
