package com.casestudy.cartservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document("profile")
public @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString class UserProfile {

	private int profileId;

	private String fullName;
	
	private String emailId;
	
	private Long mobileNumber;
	
	private String role;
	
	private String password;
}
