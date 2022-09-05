package com.capg.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public  @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString class Product {

	private int productId;
	
	private String productName;
	
}
