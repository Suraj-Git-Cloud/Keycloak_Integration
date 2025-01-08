package com.authservice.service;

import java.util.List;

import com.authservice.model.Product;
import com.authservice.model.UserInfo;

public interface ProductService {
	
	List<Product> getAllProducts();
	
	Product getProductById( int id);
	
	String addUser(UserInfo user);
}
