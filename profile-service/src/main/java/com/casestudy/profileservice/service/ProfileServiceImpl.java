package com.casestudy.profileservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.profileservice.model.Role;
import com.casestudy.profileservice.model.UserProfile;
import com.casestudy.profileservice.repository.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService{
	
	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public UserProfile addNewCustomerProfile(UserProfile user) {
		user.setRole(Role.Customer);
		return profileRepository.save(user);
	}

	@Override
	public List<UserProfile> getAllProfiles() {
		return profileRepository.findAll();
	}

	@Override
	public UserProfile getByProfileId(int id) {
		return profileRepository.findByProfileId(id);
	}

	@Override
	public UserProfile updateProfile(UserProfile user) {
		UserProfile oldUser = profileRepository.findByProfileId(user.getProfileId());
		oldUser.setAbout(user.getAbout());
		oldUser.setAddress(user.getAddress());
		oldUser.setDateOfBirth(user.getDateOfBirth());
		oldUser.setEmailId(user.getEmailId());
		oldUser.setFullName(user.getFullName());
		oldUser.setGender(user.getGender());
		oldUser.setImage(user.getImage());
		oldUser.setMobileNumber(user.getMobileNumber());
		oldUser.setPassword(user.getPassword());
		oldUser.setRole(user.getRole());
		return profileRepository.save(oldUser);
	}

	@Override
	public void deleteProfile(int id) {
		profileRepository.deleteById(id);
	}

	@Override
	public UserProfile addNewMerchantProfile(UserProfile user) {
		user.setRole(Role.Merchant);
		return profileRepository.save(user);
	}

	@Override
	public UserProfile addNewDeliveryProfile(UserProfile user) {
		user.setRole(Role.DeliveryAgent);
		return profileRepository.save(user);
	}

	@Override
	public UserProfile findByMobileNumber(Long mobileNumber) {
		return profileRepository.findByMobileNumber(mobileNumber);
	}

	@Override
	public UserProfile getByUserName(String name) {
		return profileRepository.findByFullName(name);
	}

	@Override
	public UserProfile getByUserEmail(String emailId) {
		return profileRepository.findByEmailId(emailId);
	}

	@Override
	public Boolean existsByEmail(String emailId) {
		return profileRepository.existsByEmailId(emailId);
	}

}
