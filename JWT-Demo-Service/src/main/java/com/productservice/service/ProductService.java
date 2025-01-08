package com.productservice.service;

import java.util.List;

import com.productservice.model.Product;
import com.productservice.model.UserInfo;

public interface ProductService {
	
	List<Product> getAllProducts();
	
	Product getProductById( int id);
	
	String addUser(UserInfo user);
}
