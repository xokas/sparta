package com.sparta.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import com.sparta.message.objects.Record;

@Component
public class MemoryDatabase {

	//private static Logger log = LogManager.getLogger(MemoryDatabase.class);
	
	private Map<String, List<Record>> records;
	
	public MemoryDatabase() {
		super();
		this.records = new HashMap<>();
	}

	public Map<String, List<Record>> getRecords() {
		return records;
	}	
}
