package com.casestudy.profileservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.casestudy.profileservice.model.UserProfile;

@Service
public interface ProfileService {
	
	public abstract UserProfile addNewCustomerProfile(UserProfile user);
	public abstract List<UserProfile> getAllProfiles();
	public abstract UserProfile getByProfileId(int id);
	public abstract UserProfile updateProfile(UserProfile user);
	public abstract void deleteProfile(int id);
	public abstract UserProfile addNewMerchantProfile(UserProfile user);
	public abstract UserProfile addNewDeliveryProfile(UserProfile user);
	public abstract UserProfile findByMobileNumber(Long mobileNumber);
	public abstract UserProfile getByUserName(String name);
	public abstract UserProfile getByUserEmail(String emailId);
	public abstract Boolean existsByEmail(String emailId);

}
