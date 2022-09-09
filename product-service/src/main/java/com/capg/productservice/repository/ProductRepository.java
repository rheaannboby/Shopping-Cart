package com.capg.productservice.repository;

import org.springframework.stereotype.Repository;

import com.capg.productservice.model.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer>{
	public Product findByProductId(int productId);
	public Product findByProductName(String productName);
	public List<Product> findByCategory(String category);
	public List<Product> findByProductType(String productType);
	//public Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
	
}
