package com.sparta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sparta.dao.impl.RecordMemDao;
import com.sparta.dao.impl.RecordMemRODao;
import com.sparta.message.objects.Record;

@Service
public class MainService {
	
	@Autowired
    @Qualifier("recordMemDao")
    private RecordMemDao recordDao;
	
	@Autowired
    @Qualifier("recordMemRODao")
    private RecordMemRODao recordRODao;
	
	public int storeDataFromProvider(String provider, List<Record> list) {
		int result = -1;
		
		result = this.recordDao.save(provider, list);
		
		return result;
	}

	public int findTotalRecordsByProvider(String provider) {
		int result = -1;
		
		result = this.recordRODao.getTotalByProvider(provider);
		
		return result;
	}

}
