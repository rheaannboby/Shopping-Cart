package com.capg.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString class Address {
	
	private int houseNumber;
	
	private String streetName;
	
	private String colonyName;
	
	private String city;

	private String state;
	
	private int pincode;
}
