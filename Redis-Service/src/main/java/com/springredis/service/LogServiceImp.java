package com.springredis.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.springredis.dao.LogRepository;
import com.springredis.model.Log;

@Service
public class LogServiceImp implements LogService {

	private final LogRepository repo;

	LogServiceImp(LogRepository dao) {
		this.repo = dao;
	}

	@Override
	public List<Log> fectAllLogDetail() {
		return repo.fectAllLogDetail();
	}

	@Override
	@Cacheable(value = "logs", key = "#id")
	public List<Log> fetchLogById(Integer id) {
		return repo.fetchLogById(id);
	}

	@Override
	public int insertLog(Log log) {
		return repo.insertLog(log);
	}

	@Override
	@CachePut(value = "logs", key = "#log.id")
	public int updateLog(Log log) {
		return repo.updateLog(log);
	}

	@Override
	@CacheEvict(value = "logs", key = "#id")
	public int deleteLogById(Integer id) {
		return repo.deleteLogById(id);
	}

}
