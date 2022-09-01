package com.capg.cartservice.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document("cart")
public @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString class Cart {
	
	@Id
	@Field
	private Integer cartId;
	
	@Field
	private List<Item> listOfItems;
	
	@Field
	private Double totalPrice;

}
