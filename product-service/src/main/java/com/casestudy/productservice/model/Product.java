package com.casestudy.productservice.model;

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

@Document("product")
public @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString class Product {
	
	@Id
	@Field
	private int productId;
	
	@Field
	private String productType;
	
	@Field
	private String productName;
	
	@Field
	private String category;
	
	@Field
	private Integer quantity;
	
	@Field
	private Map<Integer,Double> rating;
	
	@Field
	private Map<Integer,String> review;
	
	@Field
	private List<String> image;
	
	@Field
	private double price;
	
	@Field
	private String description;
	
	@Field
	private Map<String,String> specification;

}
