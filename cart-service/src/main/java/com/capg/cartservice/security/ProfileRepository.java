package com.capg.cartservice.security;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.capg.cartservice.model.UserProfile;


@Repository
public interface ProfileRepository extends MongoRepository<UserProfile, Integer>{
	
	public UserProfile findByEmailId(String emailId);
}
