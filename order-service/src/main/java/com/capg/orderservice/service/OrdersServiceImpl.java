package com.capg.orderservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.orderservice.model.Address;
import com.capg.orderservice.model.Cart;
import com.capg.orderservice.model.Orders;
import com.capg.orderservice.model.Product;
import com.capg.orderservice.repository.OrderRepository;

@Service
public class OrdersServiceImpl implements OrdersService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<Orders> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public void placeOrder(Cart cart) {
		List<Orders> orders = cart.getListOfItems().stream().map(item -> 
		{
			Orders order = new Orders();
			order.setOrderId(cart.getCartId());  // not correct
			order.setCustomerId(cart.getCartId());   // not correct
			order.setAmountPaid(item.getPrice()*item.getQuantity());
			order.setOrderStatus("ORDERED");
			order.setQuantity(item.getQuantity());
			order.setProduct(new Product());
			order.getProduct().setProductId(item.getItemId());
			order.getProduct().setProductName(item.getProductName());
			return order;
		}).collect(Collectors.toList());
		
		for(Orders order : orders) {
			orderRepository.save(order);
		}
		
	}

	@Override
	public String changeStatus(String status, int orderId) {
		Orders order = orderRepository.findOrderByOrderId(orderId);
		order.setOrderStatus(status);
		orderRepository.save(order);
		return order.getOrderStatus();
	}

	@Override
	public void deleteOrder(int orderId) {
		orderRepository.deleteById(orderId);
		
	}

	@Override
	public List<Orders> getOrderByCustomerId(Integer customerId) {
		return orderRepository.findOrderByCustomerId(customerId);
	}

	@Override
	public void storeAddress(Address address, int orderId) {
		Orders order = orderRepository.findOrderByOrderId(orderId);
		order.setAddress(address);
		orderRepository.save(order);
	}

	@Override
	public Orders getOrderById(Integer orderId) {
		return orderRepository.findOrderByOrderId(orderId);
	}

}
