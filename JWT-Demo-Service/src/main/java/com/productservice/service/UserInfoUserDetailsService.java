package com.productservice.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.productservice.model.UserInfo;
import com.productservice.repository.UserInfoRepository;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = repository.findByName(username);
        
        if(userInfo!=null) {
        	
        	UserInfoUserDetails obj = new UserInfoUserDetails();
        	
        	obj.setName(userInfo.getName());
    		obj.setPassword(userInfo.getPassword());
    		obj.setAuthorities(Arrays.stream(userInfo.getRoles().split(",")).map(SimpleGrantedAuthority::new)
    				.collect(Collectors.toList()));
        	return obj;
        }
        else {
        	throw new UsernameNotFoundException("User not found " + username);
        }      
        
    }
}