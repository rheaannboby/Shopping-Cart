package com.casestudy.cartservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import org.springframework.data.annotation.Transient;

public @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString class Item {
	
	private Integer itemId;
	
	@Transient
	private String productName;
	
	@Transient
	private Double price;
	
	@Transient
	private List<String> image;
	
	private Integer quantity;
	
	public Item(Item item) {
		itemId = item.getItemId();
		productName = item.getProductName();
		price = item.getPrice();
		quantity = item.getQuantity();
	}

}
