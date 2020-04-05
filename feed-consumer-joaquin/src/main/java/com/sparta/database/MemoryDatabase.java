package com.sparta.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import com.sparta.message.objects.Record;

@Component
public class MemoryDatabase {

	static Logger log = LogManager.getLogger(MemoryDatabase.class);
	
	private Map<String, List<Record>> records;

	public Map<String, List<Record>> getRecords() {
		if(this.records == null) {
			this.records = new HashMap<>();
		}
		return records;
	}	
}
