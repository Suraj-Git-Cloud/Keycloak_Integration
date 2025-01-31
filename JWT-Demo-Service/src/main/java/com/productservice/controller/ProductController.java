package com.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productservice.model.AuthRequest;
import com.productservice.model.Product;
import com.productservice.model.UserInfo;
import com.productservice.service.JwtService;
import com.productservice.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager; // In Spring Boot3 we need to explicitlyprovide bean of authentication manager
	
	
	
	@GetMapping("/welcome")
	public String testController()	{
		return "This is Product Controller";
	}	
	
	@GetMapping("/all")									//to-be accessed only by USER
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Product> getAllProducts() {
		return service.getAllProducts();
		
	}
	
	@GetMapping("/{id}") 									//to-be accessed only by USER
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Product getProductById(@PathVariable int id) {
		return service.getProductById(id);
	}
	
	@PostMapping("/new")										//to-be accessed only by USER
	public String addNewUser(@RequestBody UserInfo userInfo) {
		return service.addUser(userInfo);
	}
	
	@PostMapping("/authenticate")
	public String authenticateAndGetUser(@RequestBody AuthRequest authRequest) {	
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return jwtService.createTokenForUser(authRequest.getUsername(), authRequest.getRoles());
		}
		else {
			throw new UsernameNotFoundException("Invalid User");
		}
		
	}
}
