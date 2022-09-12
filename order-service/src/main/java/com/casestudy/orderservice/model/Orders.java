package com.casestudy.orderservice.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import org.springframework.data.annotation.Transient;

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
	private String orderId;
	
	@Field
	private LocalDate orderDate;
	
	@Field
	private Integer customerId;
	
	@Transient
	private String fullName;
	
	@Transient
	private Long mobileNumber;
	
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
