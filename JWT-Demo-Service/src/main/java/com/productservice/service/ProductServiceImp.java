package com.productservice.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.productservice.model.Product;
import com.productservice.model.UserInfo;
import com.productservice.repository.UserInfoRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	private UserInfoRepository repo;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	List<Product> productList = null;

	@PostConstruct
	public List<Product> loadProductsFmDB() {

		productList = IntStream.rangeClosed(1, 100).mapToObj(k -> {

			Product p = new Product();
			p.setProductId(k);
			p.setName("Product_" + k);
			p.setQty(k * 2);
			p.setPrice(k * (5.5));
			return p;
		}).collect(Collectors.toList());

		return productList;

	}

	@Override
	public List<Product> getAllProducts() {

		return productList;
	}

	@Override
	public Product getProductById(int id) {
		return productList.stream().filter(product -> product.getProductId() == id).findAny()
				.orElseThrow(() -> new RuntimeException("Poduct_" + id + " Not Found"));
	}

	@Override
	public String addUser(UserInfo userInfo) {
		System.out.println("start add User");
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		
		repo.save(userInfo);
		System.out.println("end add User");

		return "User Added Successfully";
	}

}
