package com.casestudy.cartservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Transient;

public @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString class Item {
	
	private Integer itemId;
	
	@Transient
	private String productName;
	
	@Transient
	private Double price;
	
	private Integer quantity;
	
	public Item(Item item) {
		itemId = item.getItemId();
		productName = item.getProductName();
		price = item.getPrice();
		quantity = item.getQuantity();
	}

}
