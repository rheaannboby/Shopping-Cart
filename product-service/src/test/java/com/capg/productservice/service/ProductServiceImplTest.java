package com.capg.productservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.inOrder;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.unitils.reflectionassert.ReflectionAssert;

import com.capg.productservice.model.Product;
import com.capg.productservice.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing ProductServiceImpl")
class ProductServiceImplTest {

	@InjectMocks
	ProductServiceImpl productService;
	
	@Mock
	ProductRepository productRepository;
	
	@BeforeEach
	void init_mocks() {
		MockitoAnnotations.openMocks(this);
	}

	@Nested
	@DisplayName("Adding a product")
	class AddProductMethod{
		
		@Test
		void testAddProduct() {
			Product product = new Product(102, null, "Pen Drive", null, 0, null, null, null, 0.0, null, null);
			when(productRepository.save(any(Product.class))).thenReturn(product);
			Product addedProduct = productService.addProduct(product);
			ReflectionAssert.assertReflectionEquals(product, addedProduct);
		}
		
	}
	
	@Nested
	@DisplayName("Getting all the products")
	class GetAllProductsMethod{
		
		@Test
		void testGetAllProducts() {
			Product product1 = new Product(102, null, "Pen Drive", null, 0, null, null, null, 0.0, null, null);
			Product product2 = new Product(102, null, "Speaker", null, 0, null, null, null, 0.0, null, null);
			List<Product> products = new ArrayList<Product>();
			products.add(product1);
			products.add(product2);
			when(productRepository.findAll()).thenReturn(products);
			List<Product> allProducts = productService.getAllProducts();
			assertIterableEquals(products, allProducts);
		}
		
	}
	
	@Nested
	@DisplayName("Getting a particular product by ID")
	class GetAllProductByIDMethod{
		
		@Test
		void testGetProductById() {
			Product product = new Product(102, null, "Pen Drive", null, 0, null, null, null, 0.0, null, null);
			when(productRepository.findByProductId(any(Integer.class))).thenReturn(product);
			Product specificProduct = productService.getProductById(102);
			ReflectionAssert.assertReflectionEquals(product, specificProduct);
		}
		
	}
	
	@Nested
	@DisplayName("Getting a particular product by name")
	class GetProductByNameMethod{
		
		@Test
		void testGetProductByName() {
			Product product = new Product(102, null, "Pen Drive", null, 0, null, null, null, 0.0, null, null);
			when(productRepository.findByProductName(any(String.class))).thenReturn(product);
			Product specificProduct = productService.getProductByName("Pen Drive");
			ReflectionAssert.assertReflectionEquals(product, specificProduct);
		}
		
	}
	
	@Nested
	@DisplayName("Update a particular product")
	class UpdateProductMethod{
		
		@Test
		void testUpdateProduct() {
			Product product = new Product(102, null, "Pen Drive", null, 0, null, null, null, 0.0, null, null);
			when(productRepository.findByProductId(any(Integer.class))).thenReturn(product);
			Product newProduct = new Product(102, "Electronics", "Pen Drive", null, 0, null, null, null, 0.0, null, null);
			when(productRepository.save(any(Product.class))).thenReturn(product);
			Product updatedProduct = productService.updateProduct(newProduct);
			ReflectionAssert.assertReflectionEquals(newProduct, updatedProduct);
		}
		
	}
	
	@Nested
	@DisplayName("Delete a particular product")
	class DeleteByProductIdMethod{
		
		@Test
		void testDeleteByProductId() {
			productService.deleteProductById(102);
			verify(productRepository, times(1)).deleteById(any(Integer.class));
		}
		
	}
	
	@Nested
	@DisplayName("Getting a particular product by category")
	class GetProductByCategoryMethod{
		
		@Test
		void testGetProductByCategory() {
			Product product = new Product(102, null, "Pen Drive", "Electronics", 0, null, null, null, 0.0, null, null);
			List<Product> products = new ArrayList<Product>();
			products.add(product);
			when(productRepository.findByCategory(any(String.class))).thenReturn(products);
			List<Product> listOfProducts = productService.getProductByCategory("Electronics");
			assertIterableEquals(products, listOfProducts);
		}
		
	}
	
	@Nested
	@DisplayName("Getting a particular product by type")
	class GetProductByTypeMethod{
		
		@Test
		void testGetProductByType() {
			Product product = new Product(102, "Electronic Device", "Pen Drive", null, 0, null, null, null, 0.0, null, null);
			List<Product> products = new ArrayList<Product>();
			products.add(product);
			when(productRepository.findByProductType(any(String.class))).thenReturn(products);
			List<Product> listOfProducts = productService.getProductByType("Electronic Device");
			assertIterableEquals(products, listOfProducts);
		}
		
	}
	
	

}
