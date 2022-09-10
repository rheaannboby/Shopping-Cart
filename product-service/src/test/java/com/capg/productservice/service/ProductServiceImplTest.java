package com.capg.productservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.inOrder;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

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
		@DisplayName("Verification")
		void testAddProduct1() {
			Product product = new Product(102, null, "Pen Drive", null, 0, null, null, null, 0.0, null, null);
			productService.addProduct(product);
			InOrder inOrder = inOrder(productRepository);
			inOrder.verify(productRepository, times(1)).save(product);
		}
		
		@Test
		@DisplayName("Add a product")
		void testAddProduct2() {
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
		@DisplayName("Verification")
		void testGetAllProducts1() {
			productService.getAllProducts();
			InOrder inOrder = inOrder(productRepository);
			inOrder.verify(productRepository, times(1)).findAll();
		}
		
		@Test
		@DisplayName("Fetch all the products")
		void testGetAllProducts2() {
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
		@DisplayName("Verification")
		void testGetProductById1() {
			productService.getProductById(102);
			InOrder inOrder = inOrder(productRepository);
			inOrder.verify(productRepository, times(1)).findByProductId(any(Integer.class));
		}
		
		@Test
		@DisplayName("Get a particular product using ID")
		void testGetProductById2() {
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
		@DisplayName("Verification")
		void testGetProductByName1() {
			productService.getProductByName("Earphones");
			InOrder inOrder = inOrder(productRepository);
			inOrder.verify(productRepository, times(1)).findByProductName(any(String.class));
		}
		
		@Test
		@DisplayName("Get a particular product using it's name")
		void testGetProductByName2() {
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
		@DisplayName("Verification")
		void testUpdateProduct1() {
			Product product = new Product(102, null, "Pen Drive", null, 0, null, null, null, 0.0, null, null);
			when(productRepository.findByProductId(any(Integer.class))).thenReturn(product);
			productService.updateProduct(product);
			InOrder inOrder = inOrder(productRepository);
			inOrder.verify(productRepository, times(1)).findByProductId(any(Integer.class));
			inOrder.verify(productRepository, times(1)).save(any(Product.class));
		}
		
		@Test
		@DisplayName("Update a product")
		void testUpdateProduct2() {
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
		@DisplayName("Verification")
		void testGetProductByCategory1() {
			productService.getProductByCategory("Electronics");
			InOrder inOrder = inOrder(productRepository);
			inOrder.verify(productRepository, times(1)).findByCategory(any(String.class));
		}
		
		@Test
		@DisplayName("Get a particular product using it's category")
		void testGetProductByCategory2() {
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
		@DisplayName("Verification")
		void testGetProductByType1() {
			productService.getProductByType("Electronic Device");
			InOrder inOrder = inOrder(productRepository);
			inOrder.verify(productRepository, times(1)).findByProductType(any(String.class));
		}
		
		@Test
		@DisplayName("Get a particular product using it's category")
		void testGetProductByType2() {
			Product product = new Product(102, "Electronic Device", "Pen Drive", null, 0, null, null, null, 0.0, null, null);
			List<Product> products = new ArrayList<Product>();
			products.add(product);
			when(productRepository.findByProductType(any(String.class))).thenReturn(products);
			List<Product> listOfProducts = productService.getProductByType("Electronic Device");
			assertIterableEquals(products, listOfProducts);
		}
		
	}
	
	

}
