package com.capg.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.productservice.model.Product;
import com.capg.productservice.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	@PostMapping()
	public void addProduct(@RequestBody Product product){
		service.addProduct(product);
	}
	
	@GetMapping()
	public List<Product> getAllProducts(){
		return service.getAllProducts();
	}
	
	@GetMapping("/id/{productId}")
	public Product getProductById(@PathVariable(value="productId") int id){
		return service.getProductById(id);
	}
	
	@GetMapping("/name/{productName}")
	public Product getProductByName(@PathVariable(value="productName") String name){
		return service.getProductByName(name);
	}
	
	@PutMapping()
	public Product updateProduct(@RequestBody Product product){
		return service.updateProduct(product);
	}
	
	@DeleteMapping("/{productId}")
	public void deleteProductById(@PathVariable(value = "productId") int id){
		service.deleteProductById(id);
	}
	
	@GetMapping("/category/{category}")
	public List<Product> getProductByCategory(@PathVariable(value="category") String category){
		return service.getProductByCategory(category);
	}
	
	@GetMapping("/type/{productType}")
	public List<Product> getProductByType(@PathVariable(value="productType") String type){
		return service.getProductByType(type);
	}

}
