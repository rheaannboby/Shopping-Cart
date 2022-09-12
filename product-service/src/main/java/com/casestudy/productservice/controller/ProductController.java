package com.casestudy.productservice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.casestudy.productservice.model.Product;
import com.casestudy.productservice.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public void addProduct(@RequestBody Product product){
		productService.addProduct(product);
	}
	
	@GetMapping
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/id/{productId}")
	public Product getProductById(@PathVariable(value="productId") int id){
		return productService.getProductById(id);
	}
	
	@GetMapping("/name/{productName}")
	public Product getProductByName(@PathVariable(value="productName") String name){
		return productService.getProductByName(name);
	}
	
	@PutMapping
	public Product updateProduct(@RequestBody Product product){
		return productService.updateProduct(product);
	}
	
	@DeleteMapping("/{productId}")
	public void deleteProductById(@PathVariable(value = "productId") int id){
		productService.deleteProductById(id);
	}
	
	@GetMapping("/category/{category}")
	public List<Product> getProductByCategory(@PathVariable(value="category") String category){
		return productService.getProductByCategory(category);
	}
	
	@GetMapping("/type/{productType}")
	public List<Product> getProductByType(@PathVariable(value="productType") String type){
		return productService.getProductByType(type);
	}

}
