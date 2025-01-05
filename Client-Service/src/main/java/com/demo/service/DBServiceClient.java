package com.demo.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.dto.DBMvcDto;

@FeignClient(name = "DB-SERVICE" )

public interface DBServiceClient {
	
	@GetMapping("/mvc/all")
	 List<DBMvcDto> fetchFromMvc();
	
	//@GetMapping("/flux/all")
	//Flux<DBFluxDto> fetchFromFlux(); Feign Client not working for flux 
	 

}
