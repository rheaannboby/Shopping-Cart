package com.capg.orderservice.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.unitils.reflectionassert.ReflectionAssert;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import com.capg.orderservice.model.Address;
import com.capg.orderservice.model.Cart;
import com.capg.orderservice.model.Items;
import com.capg.orderservice.model.Orders;
import com.capg.orderservice.model.Product;
import com.capg.orderservice.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing OrderServiceImpl")
class OrdersServiceImplTest {

	@InjectMocks
	OrdersServiceImpl orderService;
	
	@Mock
	OrderRepository orderRepository;
	
	@BeforeEach
	void init_mocks() {
		MockitoAnnotations.openMocks(this);
	}

	@Nested
	@DisplayName("Get all the orders")
	class GetAllOrdersMethod{
		
		@Test
		@DisplayName("Verification")
		void testGetAllOrders1() {
			InOrder inOrder = inOrder(orderRepository);
			orderService.getAllOrders();
			inOrder.verify(orderRepository, times(1)).findAll();
		}
		
		@Test
		@DisplayName("Get all orders")
		void testGetAllOrders2() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			Product product = new Product(102, "Headphone", 2000.0);
			Orders order = new Orders("101", null,(Integer) 102, "Harry Potter",(Long) Long.parseLong("9867547686"),(Double) 450.0, "CASH ON DELIVERY", "ORDERED", 2, address, product);
			List<Orders> orders = new ArrayList<Orders>();
			orders.add(order);
			when(orderRepository.findAll()).thenReturn(orders);
			List<Orders> receivedOrders = orderService.getAllOrders();
			ReflectionAssert.assertReflectionEquals(orders, receivedOrders);
		}
		
	}
	
	@Nested
	@DisplayName("Place order")
	class PlaceOrderMethod{
		
		@Test
		@DisplayName("Verification")
		void testPlaceOrder1() {
			Items item = new Items(101, "Bluetooth speaker", 6000.0, 3);
			List<Items> items = new ArrayList<Items>();
			items.add(item);
			Cart cart = new Cart(102, items, 18000.0);
			InOrder inOrder = inOrder(orderRepository);
			orderService.placeOrder(cart, "CASH ON DELIVERY");
			inOrder.verify(orderRepository, times(1)).save(any(Orders.class));
		}
	}
	
	@Nested
	@DisplayName("Change Status")
	class ChangeStatusMethod{
		
		@Test
		@DisplayName("Verification")
		void testChangeStatus1() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			Product product = new Product(102, "Headphone", 2000.0);
			Orders order = new Orders("101", null,(Integer) 102, "Harry Potter",(Long) Long.parseLong("9867547686"),(Double) 450.0, "CASH ON DELIVERY", null, 2, address, product);
			when(orderRepository.findOrderByOrderId(any(String.class))).thenReturn(order);
			order.setOrderStatus("ORDERED");
			when(orderRepository.save(any(Orders.class))).thenReturn(order);
			InOrder inOrder = inOrder(orderRepository);
			orderService.changeStatus("ORDERED", "101");
			inOrder.verify(orderRepository, times(1)).findOrderByOrderId(any(String.class));
			inOrder.verify(orderRepository, times(1)).save(any(Orders.class));
		}
		
		@Test
		@DisplayName("Change status of order")
		void testChangeStatus2() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			Product product = new Product(102, "Headphone", 2000.0);
			Orders order = new Orders("101", null,(Integer) 102, "Harry Potter",(Long) Long.parseLong("9867547686"),(Double) 450.0, "CASH ON DELIVERY", null, 2, address, product);
			when(orderRepository.findOrderByOrderId(any(String.class))).thenReturn(order);
			order.setOrderStatus("ORDERED");
			when(orderRepository.save(any(Orders.class))).thenReturn(order);
			String status = orderService.changeStatus("ORDERED", "101");
			assertEquals("ORDERED", status);
		}
		
	}
	
	@Nested
	@DisplayName("Delete Order")
	class DeleteOrderMethod{
		
		@Test
		@DisplayName("Verification")
		void testDeleteOrder() {
			InOrder inOrder = inOrder(orderRepository);
			orderService.deleteOrder("101");
			inOrder.verify(orderRepository, times(1)).deleteById("101");
		}		
	}
	
	@Nested
	@DisplayName("Get order by customer ID")
	class GetOrderByCustomerIdMethod{
		
		@Test
		@DisplayName("Verification")
		void testGetOrderByCustomerId1() {
			InOrder inOrder = inOrder(orderRepository);
			orderService.getOrderByCustomerId(101);
			inOrder.verify(orderRepository, times(1)).findOrderByCustomerId(any(Integer.class));
		}
		
		@Test
		@DisplayName("Get an order by customer ID")
		void testGetOrderByCustomerId2() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			Product product = new Product(102, "Headphone", 2000.0);
			Orders order = new Orders("101", null,(Integer) 101, "Harry Potter",(Long) Long.parseLong("9867547686"),(Double) 450.0, "CASH ON DELIVERY", null, 2, address, product);
			List<Orders> orders = new ArrayList<Orders>();
			orders.add(order);
			when(orderRepository.findOrderByCustomerId(any(Integer.class))).thenReturn(orders);
			List<Orders> customerOrders = orderService.getOrderByCustomerId(101);
			assertIterableEquals(orders, customerOrders);
		}
		
	}
	
	@Nested
	@DisplayName("Store address")
	class StoreAddressMethod{
		
		@Test
		@DisplayName("Verification")
		void testStoreAddress1() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			Product product = new Product(102, "Headphone", 2000.0);
			Orders order = new Orders("101", null,(Integer) 101, "Harry Potter",(Long) Long.parseLong("9867547686"),(Double) 450.0, "CASH ON DELIVERY", null, 2, address, product);
			InOrder inOrder = inOrder(orderRepository);
			when(orderRepository.findOrderByOrderId(any(String.class))).thenReturn(order);
			orderService.storeAddress(address, "101");
			inOrder.verify(orderRepository, times(1)).findOrderByOrderId(any(String.class));
			inOrder.verify(orderRepository, times(1)).save(any(Orders.class));
		}
		
		@Test
		@DisplayName("Store an address")
		void testStoreAddress2() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			Product product = new Product(102, "Headphone", 2000.0);
			Orders order = new Orders("101", null,(Integer) 101, "Harry Potter",(Long) Long.parseLong("9867547686"),(Double) 450.0, "CASH ON DELIVERY", null, 2, address, product);
			when(orderRepository.findOrderByOrderId(any(String.class))).thenReturn(order);
			Address newAddress = new Address(205, null, null, "Bangalore", "Karnataka", 560090);
			order.setAddress(newAddress);
			when(orderRepository.save(any(Orders.class))).thenReturn(order);
			Orders newOrder = orderService.storeAddress(address, "101");
			ReflectionAssert.assertReflectionEquals(order, newOrder);
		}
		
	}

	@Nested
	@DisplayName("Get order by ID")
	class GetOrderByIdMethod{
		
		@Test
		@DisplayName("Verification")
		void testGetOrderById1() {
			InOrder inOrder = inOrder(orderRepository);
			orderService.getOrderById("101");
			inOrder.verify(orderRepository, times(1)).findOrderByOrderId(any(String.class));
		}
		
		@Test
		@DisplayName("Get an order by ID")
		void testGetOrderById2() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			Product product = new Product(102, "Headphone", 2000.0);
			Orders order = new Orders("101", null,(Integer) 101, "Harry Potter",(Long) Long.parseLong("9867547686"),(Double) 450.0, "CASH ON DELIVERY", null, 2, address, product);
			when(orderRepository.findOrderByOrderId(any(String.class))).thenReturn(order);
			Orders receivedOrder = orderService.getOrderById("101");
			ReflectionAssert.assertReflectionEquals(order, receivedOrder);
		}
		
	}

}
