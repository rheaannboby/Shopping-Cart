package com.casestudy.orderservice.model;

import java.time.LocalDate;
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

public @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString class UserProfile {

	private int profileId;

	private String fullName;

	private String image;

	private String emailId;

	private Long mobileNumber;

	private List<Address> address;

	private String about;

	private LocalDate dateOfBirth;

	private String gender;

	private String role;

	private String password;
}
