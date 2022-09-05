package com.capg.orderservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.orderservice.model.Address;
import com.capg.orderservice.model.Cart;
import com.capg.orderservice.model.Orders;
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
			order.setAmountPaid(item.getPrice()*item.getQuantity());
			order.setOrderStatus("ORDERED");
			order.setQuantity(item.getQuantity());
			return order;
		}).collect(Collectors.toList());
		orders.stream().map(order ->
		{
			orderRepository.save(order);
			return order;
		}).close();
		
	}

	@Override
	public String changeStatus(String status, int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(int orderId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Orders> getOrderByCustomerId(Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeAddress(Address address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Orders getOrderById(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}
