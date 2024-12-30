package com.userservice.service;

import com.userservice.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
	
	Mono<User> insert(User user);
	
	Flux<User> fetchAll();
	
	Mono<User> fetchUser(Integer userId);
	
	Mono<User> update(Integer userId, User user);
	
	Mono<Void>  delete(Integer userId);
	
	Flux<User> search(String query);	

}
