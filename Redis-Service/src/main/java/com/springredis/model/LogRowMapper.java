package com.springredis.model;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LogRowMapper implements RowMapper<Log> {
	    @Override
	    public Log mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Log log = new Log();
	        log.setId(rs.getInt("id"));
	        log.setLogData(rs.getString("logData"));
	        return log;
	    }
	}