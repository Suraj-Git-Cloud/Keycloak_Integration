package com.springredis.service;

import java.util.List;

import com.springredis.model.Log;

public interface LogService {

	List<Log> fectAllLogDetail();
	List<Log> fetchLogById(Integer id);
	int insertLog(Log log);
	int updateLog(Log log);
	int deleteLogById(Integer id);
	
}
