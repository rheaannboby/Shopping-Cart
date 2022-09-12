package com.casestudy.orderservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.casestudy.orderservice.model.Address;
import com.casestudy.orderservice.model.Cart;
import com.casestudy.orderservice.model.Orders;

@Service
public interface OrdersService {
	
	public abstract List<Orders> getAllOrders();
	public abstract void placeOrder(Cart cart, String modeOfPayment);
	public abstract String changeStatus(String status, String orderId);
	public abstract void deleteOrder(String orderId);
	public abstract List<Orders> getOrderByCustomerId(Integer customerId);
	public abstract Orders storeAddress(Address address, String orderId);
	public abstract Orders getOrderById(String orderId);

}
