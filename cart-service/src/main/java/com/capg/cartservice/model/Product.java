package com.capg.cartservice.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString class Product {
	
	private int productId;

	private String productType;

	private String productName;

	//private String category;

	private Map<Integer,Double> rating;

	private Map<Integer,String> review;
	
	private List<String> image;
	
	private double price;
	
	private String description;

	private Map<String,String> specification;

}