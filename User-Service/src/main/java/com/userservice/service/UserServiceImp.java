package com.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.model.User;
import com.userservice.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	UserRepository userRepo;

	@Override
	public Mono<User> insert(User user) {
	
		Mono<User> createdUser = userRepo.save(user);
		return createdUser;
	}

	@Override
	public Flux<User> fetchAll() {
		
		Flux<User> users = userRepo.findAll();
		return users;
	}

	@Override
	public Mono<User> fetchUser(Integer userId) {

		Mono<User> user = userRepo.findById(userId);
		return user;
	}

	@Override
	public Mono<User> update(Integer userId, User user) {
		
		Mono<User> prevUser = userRepo.findById(userId);
		
		return prevUser.flatMap(user1 -> {
			
			user1.setName(user.getName());
			user1.setType(user.getType());	
			return userRepo.save(user1);
		});	
		
	}

	@Override
	public Mono<Void> delete(Integer userId) {
		
		return userRepo.findById(userId).flatMap(user->userRepo.delete(user));
	}

	@Override
	public Flux<User> search(String query) {
		return null;
	}
	
	

}
