package com.capg.orderservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.capg.orderservice.model.Orders;

@Repository
public interface OrderRepository extends MongoRepository<Orders, String> {
	List<Orders> findOrderByCustomerId(Integer id);
	Orders findOrderByOrderId(String id);
	Orders findFirstByOrderByOrderIdDesc();
}
