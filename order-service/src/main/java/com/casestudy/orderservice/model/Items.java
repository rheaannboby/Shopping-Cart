package com.casestudy.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString class Items {
	
	private Integer itemId;
	
	private String productName;
	
	private Double price;
	
	private Integer quantity;
	
	public Items(Items item) {
		itemId = item.getItemId();
		productName = item.getProductName();
		price = item.getPrice();
		quantity = item.getQuantity();
	}

}
