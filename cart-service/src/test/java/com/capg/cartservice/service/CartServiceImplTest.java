package com.capg.cartservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.unitils.reflectionassert.ReflectionAssert;

import com.capg.cartservice.model.Cart;
import com.capg.cartservice.model.Item;
import com.capg.cartservice.repository.CartRepository;


class CartServiceImplTest {

	@InjectMocks
	CartServiceImpl cartService;
	
	@Mock
	CartRepository cartRepository;
	
	@BeforeEach
	void init_mocks() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetCartById() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllItems() {
		fail("Not yet implemented");
	}

	@Test
	void testAddToCart() {
		Item item = new Item(101, "Bluetooth speaker", 6000.0, 3);
		List<Item> items = new ArrayList<Item>();
		items.add(item);
		Cart cart = new Cart(102, items, 18000.0);
		System.err.println(cart);
		when(cartRepository.save(cart)).thenReturn(cart);
		when(cartRepository.existsById(102)).thenReturn(false);
		Cart savedCart = cartService.addToCart(102, item);
		ReflectionAssert.assertReflectionEquals(savedCart, cart);
	}

	@Test
	void testUpdateInCart() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteFromCart() {
		fail("Not yet implemented");
	}

}
