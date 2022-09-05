package com.capg.profileservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.capg.profileservice.model.UserProfile;

@Repository
public interface ProfileRepository extends MongoRepository<UserProfile, Integer>{
	
	public UserProfile findByMobileNumber(Long mobileNumber);
	public UserProfile findByFullName(String fullName);
	public UserProfile findByProfileId(int Id);
}
