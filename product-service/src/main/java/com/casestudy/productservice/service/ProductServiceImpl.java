package com.casestudy.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.productservice.model.Product;
import com.casestudy.productservice.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);		
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(int productId) {
		return productRepository.findByProductId(productId);
	}

	@Override
	public Product getProductByName(String productName) {
		return productRepository.findByProductName(productName);
	}

	@Override
	public Product updateProduct(Product updatedProduct) {
		Product product = productRepository.findByProductId(updatedProduct.getProductId());
		product.setCategory(updatedProduct.getCategory());
		product.setDescription(updatedProduct.getDescription());
		product.setImage(updatedProduct.getImage());
		product.setPrice(updatedProduct.getPrice());
		product.setProductName(updatedProduct.getProductName());
		product.setProductType(updatedProduct.getProductType());
		product.setRating(updatedProduct.getRating());
		product.setReview(updatedProduct.getReview());
		product.setQuantity(updatedProduct.getQuantity());
		product.setSpecification(updatedProduct.getSpecification());
		return productRepository.save(product);
	}

	@Override
	public void deleteProductById(int productId) {
		productRepository.deleteById(productId);	
	}

	@Override
	public List<Product> getProductByCategory(String category) {
		return productRepository.findByCategory(category);
	}

	@Override
	public List<Product> getProductByType(String productType) {
		return productRepository.findByProductType(productType);
	}

	@Override
	public Product updateProductQty(Product updatedProduct) {
		Product product = productRepository.findByProductId(updatedProduct.getProductId());
		product.setQuantity(updatedProduct.getQuantity());
		return productRepository.save(product);
	}

	@Override
	public Integer getProductQtyById(int productId) {
		Product product = productRepository.findByProductId(productId);
		return product.getQuantity();
	}
	
}
