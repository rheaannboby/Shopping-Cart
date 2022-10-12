package com.casestudy.profileservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.profileservice.model.UserProfile;
import com.casestudy.profileservice.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired 
	private ProfileService profileService;

	@PostMapping("/customer")
	public UserProfile addNewCustomer(@RequestBody UserProfile user) {
		return profileService.addNewCustomerProfile(user);
	}
	
	@GetMapping("/all")
	public List<UserProfile> getAllProfiles(){
		return profileService.getAllProfiles();
	}
		
	@GetMapping("/{id}")
	public UserProfile getProfileById(@PathVariable(value="id") int profileId){
		return profileService.getByProfileId(profileId);
	}
	
	@PutMapping()
	public UserProfile updateProfile(@RequestBody UserProfile user) {
		return profileService.updateProfile(user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProfile(@PathVariable(value="id") int profileId) {
		profileService.deleteProfile(profileId);
	}
	
	@PostMapping("/merchant")
	public UserProfile addNewMerchant(@RequestBody UserProfile user) {
		return profileService.addNewMerchantProfile(user);
	}
	
	@PostMapping("/delivery")
	public UserProfile addNewDeliveryAgent(@RequestBody UserProfile user) {
		return profileService.addNewDeliveryProfile(user);
	}
		
	@GetMapping("/mobile/{number}")
	public UserProfile getProfileByMobileNumber(@PathVariable(value="number") Long mobileNumber) {
		return profileService.findByMobileNumber(mobileNumber);
	}
	
	
	@GetMapping("/name/{userName}")
	public UserProfile getByUserName(@PathVariable(value="userName") String userName) {
		return profileService.getByUserName(userName);
	}
	
	@GetMapping("/email/{emailId}")
	public UserProfile getByUserEmail(@PathVariable(value="emailId") String emailId) {
		return profileService.getByUserEmail(emailId);
	}
	
	@GetMapping("/exists/{emailId}")
	public Boolean existsByEmail(@PathVariable(value="emailId") String emailId) {
		return profileService.existsByEmail(emailId);
	}

}
