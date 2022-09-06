package com.capg.cartservice.service;

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

import com.capg.cartservice.model.Cart;
import com.capg.cartservice.model.Item;
import com.capg.cartservice.repository.CartRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing CartServiceImpl")
class CartServiceImplTest {

	@InjectMocks
	CartServiceImpl cartService;
	
	@Mock
	CartRepository cartRepository;
	
	@BeforeEach
	void init_mocks() {
		MockitoAnnotations.openMocks(this);
	}

	@Nested
	@DisplayName("Get a cart by ID")
	class GetCartByIdMethod{
		
		@Test
		@DisplayName("Verification")
		void testGetCartById1() {
			cartService.getCartById(102);
			InOrder inOrder = inOrder(cartRepository);
			inOrder.verify(cartRepository, times(1)).findByCartId(any(Integer.class));
		}
		
		@Test
		@DisplayName("Get the cart details by passing valid cart ID")
		void testGetCartById2() {
			Item item = new Item(101, "Bluetooth speaker", 6000.0, 3);
			List<Item> items = new ArrayList<Item>();
			items.add(item);
			Cart cart = new Cart(102, items, 18000.0);
			when(cartRepository.findByCartId(any(Integer.class))).thenReturn(cart);
			Cart receivedCart = cartService.getCartById(102);
			ReflectionAssert.assertReflectionEquals(cart, receivedCart);
		}
	}

	@Nested
	@DisplayName("Get all the items in a cart")
	class GetAllItemsMethod{
		
		@Test
		@DisplayName("Verification")
		void testGetAllItems1() {
			Item item1 = new Item(101, "Bluetooth speaker", 6000.0, 3);
			List<Item> items = new ArrayList<Item>();
			items.add(item1);
			Cart cart = new Cart(102, items, 18000.0);
			when(cartRepository.findByCartId(any(Integer.class))).thenReturn(cart);
			cartService.getAllItems(102);
			InOrder inOrder = inOrder(cartRepository);
			inOrder.verify(cartRepository, times(1)).findByCartId(any(Integer.class));
		}
		
		@Test
		@DisplayName("Get the items in a cart by passing valid cart ID")
		void testGetAllItems2() {
			Item item1 = new Item(101, "Bluetooth speaker", 6000.0, 3);
			Item item2 = new Item(102, "Phone", 12000.0, 1);
			List<Item> items = new ArrayList<Item>();
			items.add(item1);
			items.add(item2);
			Cart cart = new Cart(102, items, 30000.0);
			when(cartRepository.findByCartId(any(Integer.class))).thenReturn(cart);
			List<Item> receivedItems = cartService.getAllItems(102);
			ReflectionAssert.assertReflectionEquals(items, receivedItems);
		}
	}

	@Nested
	@DisplayName("Adding items to cart")
	class AddToCartMethod{	

		@Test
		@DisplayName("Verifying if methods are called in order 1")
		void testAddToCart1() {
			Item newItem = new Item(101, "Bluetooth speaker", 6000.0, 3);
			List<Item> items = new ArrayList<Item>();
			items.add(newItem);
			Cart cart = new Cart(102, items, 18000.0);
			when(cartRepository.existsById(any(Integer.class))).thenReturn(false);
			cartService.addToCart(102, newItem);
			InOrder inOrder = inOrder(cartRepository);
			inOrder.verify(cartRepository, times(1)).existsById(any(Integer.class));
			inOrder.verify(cartRepository, times(0)).findByCartId(any(Integer.class));
			inOrder.verify(cartRepository, times(1)).save(any(Cart.class));		
		}
		
		@Test
		@DisplayName("Verifying if methods are called in order 2")
		void testAddToCart2() {
			Item newItem = new Item(101, "Bluetooth speaker", 6000.0, 3);
			List<Item> items = new ArrayList<Item>();
			items.add(newItem);
			Cart cart = new Cart(102, items, 18000.0);
			when(cartRepository.existsById(any(Integer.class))).thenReturn(true);
			when(cartRepository.findByCartId(any(Integer.class))).thenReturn(cart);
			cartService.addToCart(102, newItem);
			InOrder inOrder = inOrder(cartRepository);
			inOrder.verify(cartRepository, times(1)).existsById(any(Integer.class));
			inOrder.verify(cartRepository, times(1)).findByCartId(any(Integer.class));
			inOrder.verify(cartRepository, times(1)).save(any(Cart.class));		
		}
		
		@Test
		@DisplayName("Adding item to a new cart")
		void testAddToCart3() {
			Item newItem = new Item(101, "Bluetooth speaker", 6000.0, 3);
			List<Item> items = new ArrayList<Item>();
			items.add(newItem);
			Cart cart = new Cart(102, items, 18000.0);
			when(cartRepository.save(any(Cart.class))).thenReturn(cart);
			when(cartRepository.existsById(any(Integer.class))).thenReturn(false);
			Cart savedCart = cartService.addToCart(102, newItem);
			//ReflectionAssert.assertReflectionEquals(savedCart, cart);
			//assertIterableEquals(items,savedCart.getListOfItems());
			assertThat(savedCart.getListOfItems()).contains(newItem);
		}
		
		@Test
		@DisplayName("Adding an item to an existing cart")
		void testAddToCart4() {
			Item newItem = new Item(101, "Bluetooth speaker", 6000.0, 3);
			Item item1 = new Item(102, "Phone", 16000.0, 1);
			Item item2 = new Item(103, "Laptop", 34000.0, 1);
			List<Item> items = new ArrayList<Item>();
			items.add(item1);
			items.add(item2);
			Cart cart = new Cart(102, items, 50000.0);
			when(cartRepository.existsById(any(Integer.class))).thenReturn(true);
			when(cartRepository.findByCartId(any(Integer.class))).thenReturn(cart);
			items.add(newItem);
			cart = new Cart(102, items, 68000.0);
			when(cartRepository.save(any(Cart.class))).thenReturn(cart);
			Cart savedCart = cartService.addToCart(102, newItem);
			assertThat(savedCart.getListOfItems()).contains(newItem);
		}
	}

	@Nested
	@DisplayName("Updating items in the cart")
	class UpdateInCartMethod{
		
		@Test
		@DisplayName("Verifying if methods are called in order 1")
		void testUpdateInCart1() {
			Item item = new Item(101, "Bluetooth speaker", 6000.0, 3);
			List<Item> items = new ArrayList<Item>();
			items.add(item);
			Cart cart = new Cart(102, items, 18000.0);
			when(cartRepository.findByCartId(any(Integer.class))).thenReturn(cart);
			cartService.updateInCart(102, item);
			InOrder inOrder = inOrder(cartRepository);
			inOrder.verify(cartRepository, times(1)).findByCartId(any(Integer.class));
			inOrder.verify(cartRepository, times(1)).save(any(Cart.class));	
		}
		
		@Test
		@DisplayName("Updating the quantity of an item")
		void testUpdateInCart2() {
			Item item = new Item(101, "Bluetooth speaker", 6000.0, 3);
			Item updateItem = new Item(101, "Bluetooth speaker", 6000.0, 2);
			List<Item> items = new ArrayList<Item>();
			items.add(item);
			Cart cart = new Cart(102, items, 18000.0);
			when(cartRepository.findByCartId(any(Integer.class))).thenReturn(cart);
			items.remove(item);
			items.add(updateItem);
			cart = new Cart(102, items, 12000.0);
			when(cartRepository.save(any(Cart.class))).thenReturn(cart);
			Item updatedItem = new Item();
			updatedItem.setItemId(101);
			updatedItem.setQuantity(2);
			updatedItem.setPrice(6000.0);
			Cart updatedCart = cartService.updateInCart(102, updatedItem);
			assertThat(updatedCart.getListOfItems()).contains(updateItem);
		}

	}

	@Nested
	@DisplayName("Deleting an item from the cart")
	class DeleteFromCartMethod{
		
		@Test
		@DisplayName("Verification")
		void testDeleteFromCart1() {
			Item item = new Item(101, "Bluetooth speaker", 6000.0, 3);
			List<Item> items = new ArrayList<Item>();
			items.add(item);
			Cart cart = new Cart(102, items, 18000.0);
			when(cartRepository.findByCartId(any(Integer.class))).thenReturn(cart);
			cartService.deleteFromCart(102, item);
			InOrder inOrder = inOrder(cartRepository);
			inOrder.verify(cartRepository, times(1)).findByCartId(any(Integer.class));
			inOrder.verify(cartRepository, times(1)).save(any(Cart.class));	
		}
		
		@Test
		@DisplayName("Deleting the last item in the cart")
		void testDeleteFromCart2() {
			Item item = new Item(101, "Bluetooth speaker", 6000.0, 3);
			List<Item> items = new ArrayList<Item>();
			items.add(item);
			Cart cart = new Cart(102, items, 18000.0);
			when(cartRepository.findByCartId(any(Integer.class))).thenReturn(cart);
			items.remove(item);
			cart = new Cart(102, items, 0.0);
			when(cartRepository.save(any(Cart.class))).thenReturn(cart);
			Cart deletedCart = cartService.deleteFromCart(102, item);
			assertThat(deletedCart.getListOfItems()).contains();
		}
		
		@Test
		@DisplayName("Deleting an item in the cart")
		void testDeleteFromCart3() {
			Item item1 = new Item(102, "Phone", 16000.0, 1);
			Item item2 = new Item(103, "Laptop", 34000.0, 1);
			List<Item> items = new ArrayList<Item>();
			items.add(item1);
			items.add(item2);
			Cart cart = new Cart(102, items, 50000.0);
			when(cartRepository.findByCartId(any(Integer.class))).thenReturn(cart);
			items.remove(item1);
			cart = new Cart(102, items, 34000.0);
			when(cartRepository.save(any(Cart.class))).thenReturn(cart);
			Cart deletedCart = cartService.deleteFromCart(102, item1);
			assertThat(deletedCart.getListOfItems()).doesNotContain(item1);
		}
	}
	
	@Nested
	@DisplayName("Deleting all the items in the cart")
	class ClearCartMethod{
		
		@Test
		@DisplayName("Verification")
		void testClearCart1() {
			Item item = new Item(101, "Bluetooth speaker", 6000.0, 3);
			List<Item> items = new ArrayList<Item>();
			items.add(item);
			Cart cart = new Cart(102, items, 18000.0);
			when(cartRepository.findByCartId(any(Integer.class))).thenReturn(cart);
			cartService.clearCart(102);
			InOrder inOrder = inOrder(cartRepository);
			inOrder.verify(cartRepository, times(1)).findByCartId(any(Integer.class));
			inOrder.verify(cartRepository, times(1)).save(any(Cart.class));	
		}
		
		@Test
		@DisplayName("Deleting every item in the cart")
		void testDeleteFromCart3() {
			Item item1 = new Item(102, "Phone", 16000.0, 1);
			Item item2 = new Item(103, "Laptop", 34000.0, 1);
			List<Item> items = new ArrayList<Item>();
			items.add(item1);
			items.add(item2);
			Cart cart = new Cart(102, items, 50000.0);
			when(cartRepository.findByCartId(any(Integer.class))).thenReturn(cart);
			items.clear();
			cart = new Cart(102, items, 0.0);
			when(cartRepository.save(any(Cart.class))).thenReturn(cart);
			Cart clearedCart = cartService.clearCart(102);
			assertThat(clearedCart.getListOfItems()).contains();
		}
	}

}


// can't use static methods like @BeforeAll, @AfterEach, etc. in Nested test classes
