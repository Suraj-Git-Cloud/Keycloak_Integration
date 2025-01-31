package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.DBFluxDto;
import com.demo.dto.DBMvcDto;
import com.demo.dto.UserServiceDto;
import com.demo.service.ClientServiceImp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	ClientServiceImp service;
	
	
	@GetMapping("/")
	public String getWelcomeMsg() {

		return service.testController();
	}
	
	@GetMapping("/db/mvc/all")
	public List<DBMvcDto> fetchFromDBServiceMvc() {
		return service.fetchFromDBServiceMvc();
		
	}
	
	
	
	@GetMapping("/db/flux/all")
	public  Flux<DBFluxDto> fetchFromDBServiceFlux() {
		return service.fetchFromDBServiceFlux() ;
		
	}
	
	
	@GetMapping("/userservice/{id}")
	public  Mono<UserServiceDto> fetchFromUserService(@PathVariable Integer id) {
		return service.fetchFromUserService(id) ;
		
	}
	

}
