package com.capg.orderservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capg.orderservice.model.Address;
import com.capg.orderservice.model.Cart;
import com.capg.orderservice.model.Orders;

@Service
public interface OrdersService {
	
	public abstract List<Orders> getAllOrders();
	public abstract void placeOrder(Cart cart);
	public abstract String changeStatus(String status, int orderId);
	public abstract void deleteOrder(int orderId);
	public abstract List<Orders> getOrderByCustomerId(Integer customerId);
	public abstract void storeAddress(Address address, int orderId);
	public abstract Orders getOrderById(Integer orderId);

}
