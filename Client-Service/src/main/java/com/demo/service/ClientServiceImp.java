package com.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.demo.dto.DBFluxDto;
import com.demo.dto.DBMvcDto;
import com.demo.dto.UserServiceDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImp {

	private DBServiceClient dbServiceClient;

	private final WebClient userServiceWebClient;
	
	private final WebClient dbServiceWebClient;
	
	public ClientServiceImp(DBServiceClient dbServiceClient, 
			WebClient.Builder webClientBuilder, WebClient.Builder dbServiceWebClient) {
		super();
		this.dbServiceClient = dbServiceClient;
		this.userServiceWebClient = webClientBuilder.baseUrl("http://localhost:8081/users").build();
		this.dbServiceWebClient = webClientBuilder.baseUrl("http://localhost:8082").build();
	}

	public List<DBMvcDto> fetchFromDBServiceMvc() {
		return dbServiceClient.fetchFromMvc();

	}

	public Flux<DBFluxDto> fetchFromDBServiceFlux() {
		return dbServiceWebClient.get().uri("/flux/all").retrieve().bodyToFlux(DBFluxDto.class);

	}

	/*
	 * public Mono<UserServiceDto> fetchFromUserService(Integer userId) { return
	 * userServiceClient.fetchUser(userId); Feign client not working somehow with
	 * flux }
	 */

	public Mono<UserServiceDto> fetchFromUserService(Integer id) {
		return userServiceWebClient.get().uri("/{id}", id).retrieve().bodyToMono(UserServiceDto.class);
	}

	public String testController() {
		return "Client-Demo-Controller";
	}

}
