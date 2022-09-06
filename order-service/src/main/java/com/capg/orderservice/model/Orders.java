package com.capg.orderservice.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document("orders")
public @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString  class Orders {

	//@GeneratedValue( strategy = GenerationType.AUTO)
	
	@Id
	@Field
	private int orderId;
	
	@Field
	private LocalDate orderDate;
	
	@Field
	private Integer customerId;
	
	@Field
	private Double amountPaid;
	
	@Field
	private String modeOfPayment;
	
	@Field
	private String orderStatus;
	
	@Field
	private int quantity;
	
	@Field
	private Address address;
	
	@Field
	private Product product;
	

}
