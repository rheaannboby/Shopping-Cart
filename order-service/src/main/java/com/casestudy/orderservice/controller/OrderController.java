package com.casestudy.orderservice.controller;

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
import org.springframework.web.client.RestTemplate;

import com.casestudy.orderservice.model.Address;
import com.casestudy.orderservice.model.Cart;
import com.casestudy.orderservice.model.Orders;
import com.casestudy.orderservice.model.Product;
import com.casestudy.orderservice.model.UserProfile;
import com.casestudy.orderservice.service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private OrdersService orderService;
	
	@GetMapping("/all")
	public List<Orders> getAllOrders(){
		List<Orders> orders = orderService.getAllOrders();
		orders.forEach(order ->{
			System.err.println(order.getProduct().getProductId());
			Product product = restTemplate.getForObject("http://PRODUCT-SERVICE/product/id/"+order.getProduct().getProductId(), Product.class);
			order.getProduct().setProductName(product.getProductName());
			order.getProduct().setPrice(product.getPrice());
			UserProfile profile = restTemplate.getForObject("http://PROFILE-SERVICE/profile/"+order.getCustomerId(), UserProfile.class);
			order.setFullName(profile.getFullName());
			order.setMobileNumber(profile.getMobileNumber());
		});
		return orders;
	}
	
	@PostMapping("/place/{mode}")
	public void placeOrder(@RequestBody Cart cart,@PathVariable(value="mode") String modeOfPayment) {
		orderService.placeOrder(cart, modeOfPayment);
	}
	
	@PutMapping("/{orderId}/{status}")
	public String changeStatus(@PathVariable(value="status")String status, @PathVariable(value="orderId") String orderId) {
		return orderService.changeStatus(status, orderId);
	}
	
	@DeleteMapping("/{orderId}")
	public void deleteOrder(@PathVariable(value="orderId") String orderId) {
		orderService.deleteOrder(orderId);
	}
	
	@GetMapping("/customer/{id}")
	public List<Orders> getOrderByCustomerId(@PathVariable(value="id") Integer customerId){
		List<Orders> orders = orderService.getOrderByCustomerId(customerId);
		orders.forEach(order ->{
			Product product = restTemplate.getForObject("http://PRODUCT-SERVICE/product/id/"+order.getProduct().getProductId(), Product.class);
			order.getProduct().setProductName(product.getProductName());
			UserProfile profile = restTemplate.getForObject("http://PROFILE-SERVICE/profile/"+order.getCustomerId(), UserProfile.class);
			order.setFullName(profile.getFullName());
			order.setMobileNumber(profile.getMobileNumber());
		});
		return orders;
	}
	
	@PutMapping("/address/{id}")
	public void storeAddress(@RequestBody Address address, @PathVariable(value="id") String orderId) {
		Orders order = orderService.storeAddress(address, orderId);
		UserProfile profile = restTemplate.getForObject("http://PROFILE-SERVICE/profile/"+order.getCustomerId(), UserProfile.class);
		if(!profile.getAddress().contains(address)) {
			profile.getAddress().add(address);
			restTemplate.put("http://PROFILE-SERVICE/profile/", profile);
		}
	}
	
	@GetMapping("/{id}")
	public Orders getOrderById(@PathVariable(value="id") String orderId){
		Orders order = orderService.getOrderById(orderId);
		Product product = restTemplate.getForObject("http://PRODUCT-SERVICE/product/id/"+order.getProduct().getProductId(), Product.class);
		order.getProduct().setProductName(product.getProductName());
		UserProfile profile = restTemplate.getForObject("http://PROFILE-SERVICE/profile/"+order.getCustomerId(), UserProfile.class);
		order.setFullName(profile.getFullName());
		order.setMobileNumber(profile.getMobileNumber());
		return order;
	}
	

}
