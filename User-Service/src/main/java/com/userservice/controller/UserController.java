package com.userservice.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.model.User;
import com.userservice.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService service;

	@PostMapping()
	public Mono<User> insert(@RequestBody User user) {

		Mono<User> insertedUser = service.insert(user);
		return insertedUser;

	}

	@GetMapping("/all")
	public Flux<User> fetchAll() {
		 return service.fetchAll().delayElements(Duration.ofSeconds(4));
		
	}

	@GetMapping("/{id}")
	public Mono<User> findById(@PathVariable("id") Integer userId) {
		Mono<User> userData = service.fetchUser(userId);
		return userData;
	}

	@PutMapping("/{id}")
	public Mono<User> update(@PathVariable("id") Integer userId, @RequestBody User user) {

		Mono<User> updatedUser = service.update(userId, user);
		return updatedUser;
	}

	@DeleteMapping("/{id}")
	public Mono<Void> delete(@PathVariable("id") Integer userId) {
		return service.delete(userId);
	}

}
