package com.capg.orderservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString class Cart {

	private Integer cartId;

	private List<Items> listOfItems;

	private Double totalPrice;

}
