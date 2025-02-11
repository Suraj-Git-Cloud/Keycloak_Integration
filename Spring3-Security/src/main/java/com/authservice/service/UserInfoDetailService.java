package com.authservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.authservice.model.UserInfo;
import com.authservice.model.UserInfoUserDetails;
import com.authservice.repository.UserInfoRepository;

@Component
public class UserInfoDetailService implements UserDetailsService {

	@Autowired
	private UserInfoRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo =  repo.findByName(username);
		
		return userInfo.map(UserInfoUserDetails::new)
		.orElseThrow(() -> new UsernameNotFoundException("User - "+username+" Not Found"));
	}

}
