package com.casestudy.cartservice;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.unitils.reflectionassert.ReflectionAssert;

import com.casestudy.cartservice.model.Cart;
import com.casestudy.cartservice.model.Item;
import com.casestudy.cartservice.repository.CartRepository;
import com.casestudy.cartservice.service.CartServiceImpl;

import org.mockito.junit.jupiter.MockitoExtension;


public class CartServiceApplicationTests {

	@InjectMocks
	CartServiceImpl service;
	
	@Mock
	CartRepository repository;
	
	@BeforeEach
	void init_mocks() {
		MockitoAnnotations.openMocks(this);
	}
		
	@Test
	@DisplayName("Add the first valid item onto a new cart")
	public void addToCartTest1() {
		repository = mock(CartRepository.class);
		Item item = new Item(101, "Bluetooth speaker", 6000.0, 3);
		List<Item> items = new ArrayList<Item>();
		items.add(item);
		Cart cart = new Cart(102, items, 18000.0);
		System.err.println(cart);
		when(repository.save(cart)).thenReturn(cart);
		when(repository.existsById(102)).thenReturn(false);
		Cart savedCart = service.addToCart(102, item);
		ReflectionAssert.assertReflectionEquals(savedCart, cart);		
	}
	
	@Test
	@DisplayName("Add the a valid item onto a cart which already has items")
	public void addToCartTest2() {
		Item item1 = new Item(102, "Bluetooth speaker", 6000.0, 3);
		Item item2 = new Item(103, "Headphones", 6000.0, 1);
		List<Item> items = new ArrayList<Item>();
		items.add(item1);
		Cart cart = new Cart(402, items, 18000.0);
		when(repository.findByCartId(402)).thenReturn(cart);
		items.add(item2);
		Cart cartNew = new Cart(402, items, 24000.0);
		Cart savedCart = service.addToCart(402, item2);
		when(repository.save(cartNew)).thenReturn(cartNew);
		System.err.println(cartNew);
		ReflectionAssert.assertReflectionEquals(savedCart, cartNew);		
	}
}
