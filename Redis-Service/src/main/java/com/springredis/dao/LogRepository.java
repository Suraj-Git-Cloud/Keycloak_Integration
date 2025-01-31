package com.springredis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springredis.model.Log;
import com.springredis.model.LogRowMapper;

@Repository
public class LogRepository {
	
	
	private final NamedParameterJdbcTemplate namedJdbcTemplateRead;

	LogRepository(@Qualifier("namedJdbcTemplateRead") NamedParameterJdbcTemplate readTemplate) {
		this.namedJdbcTemplateRead = readTemplate;
	}

	public List<Log> fectAllLogDetail() {
		System.out.println(" ------------------ Actual Call to Database to pull  ALL Logs ---------------------");
		
		String sql = "SELECT id, logData FROM LogDetail";
		
	    return namedJdbcTemplateRead.query(sql, new LogRowMapper());

	}
	
	public List<Log> fetchLogById(Integer id) {
	    System.out.println(" ------------------ Actual Call to Database To FETCH Log by Id---------------------");

	    String sql = "SELECT id, logData FROM LogDetail WHERE id = :id";
	    
	    Map<String, Object> params = new HashMap<>();
	    params.put("id", id);
	    return namedJdbcTemplateRead.query(sql, params, new LogRowMapper());
	}

	
	public int insertLog(Log log) {
	    System.out.println(" ------------------ Actual Call to Database To INSERT Log ---------------------");

	    String sql = "INSERT INTO LogDetail (id, logData) VALUES (:id, :logData)";

	    Map<String, Object> params = new HashMap<>();
	    params.put("id", log.getId());
	    params.put("logData", log.getLogData());
	    return namedJdbcTemplateRead.update(sql, params);
	}
	
	
	public int updateLog(Log log) {
	    System.out.println(" ------------------ Actual Call to Database To UPDATE Log ---------------------");

	    String sql = "UPDATE LogDetail SET logData = :logData WHERE id = :id";

	    Map<String, Object> params = new HashMap<>();
	    params.put("id", log.getId());
	    params.put("logData", log.getLogData());

	    return namedJdbcTemplateRead.update(sql, params);
	}

	
	public int deleteLogById(Integer id) {
	    System.out.println(" ------------------ Actual Call to Database To DELETE By Id ---------------------");

	    String sql = "DELETE FROM LogDetail WHERE id = :id";

	    Map<String, Object> params = new HashMap<>();
	    params.put("id", id);
	    return namedJdbcTemplateRead.update(sql, params);
	}


}
