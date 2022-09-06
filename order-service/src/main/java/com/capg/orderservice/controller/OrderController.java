package com.capg.orderservice.controller;

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

import com.capg.orderservice.model.Address;
import com.capg.orderservice.model.Cart;
import com.capg.orderservice.model.Orders;
import com.capg.orderservice.service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrdersService orderService;
	
	@GetMapping("/all")
	public List<Orders> getAllOrders(){
		return orderService.getAllOrders();
	}
	
	@PostMapping("/place")
	public void placeOrder(@RequestBody Cart cart) {
		orderService.placeOrder(cart);
	}
	
	@PutMapping("/{orderId}/{status}")
	public String changeStatus(@PathVariable(value="status")String status, @PathVariable(value="orderId") int orderId) {
		return orderService.changeStatus(status, orderId);
	}
	
	@DeleteMapping("/{orderId}")
	public void deleteOrder(@PathVariable(value="orderId") int orderId) {
		orderService.deleteOrder(orderId);
	}
	
	@GetMapping("/customer/{id}")
	public List<Orders> getOrderByCustomerId(@PathVariable(value="id") Integer customerId){
		return orderService.getOrderByCustomerId(customerId);
	}
	
	@PutMapping("/address/{id}")
	public void storeAddress(@RequestBody Address address, @PathVariable(value="id") int orderId) {
		orderService.storeAddress(address, orderId);
	}
	
	@GetMapping("/{id}")
	public Orders getOrderById(@PathVariable(value="id") int orderId){
		return orderService.getOrderById(orderId);
	}
	

}
