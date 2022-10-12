package com.casestudy.orderservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.casestudy.orderservice.model.Address;
import com.casestudy.orderservice.model.Cart;
import com.casestudy.orderservice.model.Items;
import com.casestudy.orderservice.model.Orders;
import com.casestudy.orderservice.model.Product;
import com.casestudy.orderservice.repository.OrderRepository;

@Service
public class OrdersServiceImpl implements OrdersService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<Orders> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public void placeOrder(Cart cart, String modeOfPayment) {
		List<Items> items = cart.getListOfItems();
		List<Orders> orders = cart.getListOfItems().stream().map(item -> 
		{
			Orders order = new Orders();
			//order.setOrderId(cart.getCartId().toString()+items.indexOf(item));  // not correct
			order.setOrderId((new ObjectId()).toString());
			order.setOrderDate(LocalDate.now());
			order.setCustomerId(cart.getCartId());   // not correct
			order.setAmountPaid(item.getPrice()*item.getQuantity());
			order.setOrderStatus("ORDERED");
			order.setQuantity(item.getQuantity());
			order.setModeOfPayment(modeOfPayment);
			order.setProduct(new Product());
			order.getProduct().setProductId(item.getItemId());
			return order;
		}).collect(Collectors.toList());
		
		for(Orders order : orders) {
			orderRepository.save(order);
		}
		
	}

	@Override
	public String changeStatus(String status, String orderId) {
		Orders order = orderRepository.findOrderByOrderId(orderId);
		order.setOrderStatus(status);
		Orders orders = orderRepository.save(order);
		return orders.getOrderStatus();
	}

	@Override
	public void deleteOrder(String orderId) {
		orderRepository.deleteById(orderId);
		
	}

	@Override
	public List<Orders> getOrderByCustomerId(Integer customerId) {
		return orderRepository.findOrderByCustomerId(customerId);
	}

	@Override
	public Orders storeAddress(Address address, String orderId) {
		Orders order = orderRepository.findOrderByOrderId(orderId);
		order.setAddress(address);
		return orderRepository.save(order);
	}

	@Override
	public Orders getOrderById(String orderId) {
		return orderRepository.findOrderByOrderId(orderId);
	}

}
