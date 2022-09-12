package com.casestudy.orderservice.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public  @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString class Product {

	private int productId;
	
	@Transient
	private String productName;
	
	@Transient
	private double price;
	
	/*
	 
	@Transient
	private String productType;
	
	
	@Transient
	private String category;
	
	@Transient
	private Integer quantity;
	
	@Transient
	private Map<Integer,Double> rating;
	
	@Transient
	private Map<Integer,String> review;
	
	@Transient
	private List<String> image;
	
	@Transient
	private String description;
	
	@Transient
	private Map<String,String> specification;
	
	*/
	
}
