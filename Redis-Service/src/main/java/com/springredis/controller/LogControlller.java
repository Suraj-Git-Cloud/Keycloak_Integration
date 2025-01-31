package com.springredis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springredis.model.Log;
import com.springredis.service.LogService;



@RestController
@RequestMapping("/logs")
public class LogControlller {
	
	@Autowired
	LogService service;

	@PostMapping()
	public Integer insert(@RequestBody Log log) {

		int status = service.insertLog(log);
		return status;

	}

	
	@GetMapping("/")
	public String testController() {
		 return "Log Controller Test";
		
	}
	@GetMapping("/all")
	public List<Log> fectAllLogDetail() {
		 return service.fectAllLogDetail();
		
	}

	@GetMapping(value ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Log> findById(@PathVariable("id") Integer logId) {
		List<Log> logData = service.fetchLogById(logId);
		return  logData;
	}

	@PutMapping("/{id}")
	public int update(@PathVariable("id") Integer logId, @RequestBody Log log) {

		int status = service.updateLog(log);
		return status;
	}

	@DeleteMapping("/{id}")
	public int delete(@PathVariable("id") Integer logId) {
		return service.deleteLogById(logId);
	}

}
