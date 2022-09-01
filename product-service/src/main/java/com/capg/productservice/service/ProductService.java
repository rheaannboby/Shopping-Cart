package com.capg.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capg.productservice.model.Product;

@Service
public interface ProductService {
	public abstract void addProduct(Product product);
	public abstract List<Product> getAllProducts();
	public abstract Product getProductById(int productId);
	public abstract Product getProductByName(String productName);
	public abstract Product updateProduct(Product product);
	public abstract void deleteProductById(int productId);
	public abstract List<Product> getProductByCategory(String category);
	public abstract List<Product> getProductByType(String productType);
}
