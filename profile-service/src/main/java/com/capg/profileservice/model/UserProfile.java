package com.capg.profileservice.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Transient;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document("profile")
public @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString class UserProfile {
	
	@Id
	@Field
	private int profileId;
	
	@Field
	private String fullName;
	
	@Field
	private String image;
	
	@Field
	private String emailId;
	
	@Field
	private Long mobileNumber;
	
	@Field
	private List<Address> address;
	
	@Field
	private String about;
	
	@Field
	private LocalDate dateOfBirth;
	
	@Field
	private String gender;
	
	@Field
	private String role;
	
	@Field
	private String password;
	
}
