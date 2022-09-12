package com.casestudy.profileservice.service;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.unitils.reflectionassert.ReflectionAssert;

import com.casestudy.profileservice.model.Address;
import com.casestudy.profileservice.model.UserProfile;
import com.casestudy.profileservice.repository.ProfileRepository;
import com.casestudy.profileservice.service.ProfileServiceImpl;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing ProfileServiceImpl")
class ProfileServiceImplTest {

	@InjectMocks
	ProfileServiceImpl profileService;
	
	@Mock
	ProfileRepository profileRepository;
	
	@BeforeEach
	void init_mocks() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Nested
	@DisplayName("Add Customer Profile")
	class AddNewCustomerProfileMethod{
		
		@Test
		@DisplayName("Verification")
		void testAddNewCustomerProfile1() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			List<Address> addresses = new ArrayList<Address>();
			addresses.add(address);
			UserProfile user = new UserProfile(101, "Harry Potter", "img", "harry@123.gmail.com",(Long) Long.parseLong("9097834526"), addresses, "I am a customer", LocalDate.now(), "Male", "CUSTOMER", "abcd4");
			InOrder inOrder = inOrder(profileRepository);
			profileService.addNewCustomerProfile(user);
			inOrder.verify(profileRepository, times(1)).save(any(UserProfile.class));
		}
		
		@Test
		@DisplayName("Adding a new Customer Profile")
		void testAddNewCustomerProfile2() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			List<Address> addresses = new ArrayList<Address>();
			addresses.add(address);
			UserProfile user = new UserProfile(101, "Harry Potter", "img", "harry@123.gmail.com",(Long) Long.parseLong("9097834526"), addresses, "I am a customer", LocalDate.now(), "Male", "CUSTOMER", "abcd4");
			when(profileRepository.save(any(UserProfile.class))).thenReturn(user);
			UserProfile addedCustomer = profileService.addNewCustomerProfile(user);
			ReflectionAssert.assertReflectionEquals(user, addedCustomer);
		}
		
	}

	@Nested
	@DisplayName("Get all profiles")
	class GetAllProfilesMethod{
		
		@Test
		@DisplayName("Verification")
		void testGetAllProfiles1() {
			InOrder inOrder = inOrder(profileRepository);
			profileService.getAllProfiles();
			inOrder.verify(profileRepository, times(1)).findAll();
		}
		
		@Test
		@DisplayName("Get all the profiles")
		void testGetAllProfiles2() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			List<Address> addresses = new ArrayList<Address>();
			addresses.add(address);
			UserProfile user1 = new UserProfile(101, "Harry Potter", "img1", "harry@123.gmail.com",(Long) Long.parseLong("9097834526"), addresses, "I am a customer", LocalDate.now(), "Male", "CUSTOMER", "abcd4");
			UserProfile user2 = new UserProfile(102, "Ron Weasley", "img2", "ron@123.gmail.com",(Long) Long.parseLong("9087894526"), addresses, "I am a merchant", LocalDate.now(), "Male", "MERCHANT", "abcd124");
			List<UserProfile> profileList = new ArrayList<UserProfile>();
			profileList.add(user1);
			profileList.add(user2);
			when(profileRepository.findAll()).thenReturn(profileList);
			List<UserProfile> profiles = profileService.getAllProfiles();
			assertIterableEquals(profileList, profiles);
		}
	}

	@Nested
	@DisplayName("Get the profile by ID")
	class GetByProfileIdMethod{
		
		@Test
		@DisplayName("Verification")
		void testGetByProfileId1() {
			InOrder inOrder = inOrder(profileRepository);
			profileService.getByProfileId(101);
			inOrder.verify(profileRepository, times(1)).findByProfileId(any(Integer.class));
		}
		
		@Test
		@DisplayName("Get a profile by ID")
		void testGetByProfileId2() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			List<Address> addresses = new ArrayList<Address>();
			addresses.add(address);
			UserProfile user = new UserProfile(101, "Harry Potter", "img1", "harry@123.gmail.com",(Long) Long.parseLong("9097834526"), addresses, "I am a customer", LocalDate.now(), "Male", "CUSTOMER", "abcd4");
			when(profileRepository.findByProfileId(any(Integer.class))).thenReturn(user);
			UserProfile profile = profileService.getByProfileId(101);
			ReflectionAssert.assertReflectionEquals(user, profile);
		}
	}

	@Nested
	@DisplayName("Update profile")
	class UpdateProfileMethod{
		
		@Test
		@DisplayName("Verification")
		void testUpdateProfile1() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			List<Address> addresses = new ArrayList<Address>();
			addresses.add(address);
			UserProfile user = new UserProfile(101, "Harry Potter", "img1", "harry@123.gmail.com",(Long) Long.parseLong("9097834526"), addresses, "I am a customer", LocalDate.now(), "Male", "CUSTOMER", "abcd4");
			when(profileRepository.findByProfileId(any(Integer.class))).thenReturn(user);
			InOrder inOrder = inOrder(profileRepository);
			profileService.updateProfile(user);
			inOrder.verify(profileRepository, times(1)).findByProfileId(any(Integer.class));
			inOrder.verify(profileRepository, times(1)).save(any(UserProfile.class));
		}
		
		@Test
		@DisplayName("Update a profile")
		void testUpdateProfile2() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			List<Address> addresses = new ArrayList<Address>();
			addresses.add(address);
			UserProfile user = new UserProfile(101, "Harry Potter", "img1", "harry@123.gmail.com",(Long) Long.parseLong("9097834526"), addresses, "I am a customer", LocalDate.now(), "Male", "CUSTOMER", "abcd4");
			when(profileRepository.findByProfileId(any(Integer.class))).thenReturn(user);
			UserProfile newUser = new UserProfile(101, "Harry Potter", "img2", "harrypotter@123.gmail.com",(Long) Long.parseLong("9097834526"), addresses, "I am a customer", LocalDate.now(), "Male", "CUSTOMER", "abcd4");
			when(profileRepository.save(any(UserProfile.class))).thenReturn(newUser);
			UserProfile updatedUser = profileService.updateProfile(user);
			ReflectionAssert.assertReflectionEquals(newUser, updatedUser);
		}
	}

	@Nested
	@DisplayName("Delete profile")
	class DeleteProfileMethod{
		
		@Test
		@DisplayName("Verification")
		void testDeleteProfile1() {
			InOrder inOrder = inOrder(profileRepository);
			profileService.deleteProfile(101);
			inOrder.verify(profileRepository, times(1)).deleteById(any(Integer.class));
		}
		
	}
	
	@Nested
	@DisplayName("Add new Merchant")
	class AddNewMerchantProfileMethod{

		@Test
		@DisplayName("Verification")
		void testAddNewMerchantProfile1() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			List<Address> addresses = new ArrayList<Address>();
			addresses.add(address);
			UserProfile user = new UserProfile(101, "Harry Potter", "img", "harry@123.gmail.com",(Long) Long.parseLong("9097834526"), addresses, "I am a MERCHANT", LocalDate.now(), "Male", "MERCHANT", "abcd4");
			InOrder inOrder = inOrder(profileRepository);
			profileService.addNewMerchantProfile(user);
			inOrder.verify(profileRepository, times(1)).save(any(UserProfile.class));
		}
		
		@Test
		@DisplayName("Adding a new Merchant Profile")
		void testAddNewMerchantProfile2() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			List<Address> addresses = new ArrayList<Address>();
			addresses.add(address);
			UserProfile user = new UserProfile(101, "Harry Potter", "img", "harry@123.gmail.com",(Long) Long.parseLong("9097834526"), addresses, "I am a MERCHANT", LocalDate.now(), "Male", "MERCHANT", "abcd4");
			when(profileRepository.save(any(UserProfile.class))).thenReturn(user);
			UserProfile addedMerchant = profileService.addNewMerchantProfile(user);
			ReflectionAssert.assertReflectionEquals(user, addedMerchant);
		}
	}
	
	@Nested
	@DisplayName("Add new Delivery Agent")
	class AddNewDeliveryProfileMethod{

		@Test
		@DisplayName("Verification")
		void testAddNewDeliveryProfile1() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			List<Address> addresses = new ArrayList<Address>();
			addresses.add(address);
			UserProfile user = new UserProfile(101, "Harry Potter", "img", "harry@123.gmail.com",(Long) Long.parseLong("9097834526"), addresses, "I am a DELIVERY AGENT", LocalDate.now(), "Male", "DELIVERY AGENT", "abcd4");
			InOrder inOrder = inOrder(profileRepository);
			profileService.addNewDeliveryProfile(user);
			inOrder.verify(profileRepository, times(1)).save(any(UserProfile.class));
		}
		
		@Test
		@DisplayName("Adding a new Merchant Profile")
		void testAddNewDeliveryProfile2() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			List<Address> addresses = new ArrayList<Address>();
			addresses.add(address);
			UserProfile user = new UserProfile(101, "Harry Potter", "img", "harry@123.gmail.com",(Long) Long.parseLong("9097834526"), addresses, "I am a DELIVERY AGENT", LocalDate.now(), "Male", "DELIVERY AGENT", "abcd4");
			when(profileRepository.save(any(UserProfile.class))).thenReturn(user);
			UserProfile addedDelivery = profileService.addNewMerchantProfile(user);
			ReflectionAssert.assertReflectionEquals(user, addedDelivery);
		}
	}

	@Nested
	@DisplayName("Find a profile by mobile number")
	class FindByMobileNumberMethod{
		
		@Test
		@DisplayName("Verification")
		void testFindByMobileNumber1() {
			InOrder inOrder = inOrder(profileRepository);
			profileService.findByMobileNumber(Long.parseLong("9097834526"));
			inOrder.verify(profileRepository, times(1)).findByMobileNumber(any(Long.class));
		}
		
		@Test
		@DisplayName("Finding a profile by mobile number")
		void testFindByMobileNumber2() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			List<Address> addresses = new ArrayList<Address>();
			addresses.add(address);
			UserProfile user = new UserProfile(101, "Harry Potter", "img", "harry@123.gmail.com",(Long) Long.parseLong("9097834526"), addresses, "I am a DELIVERY AGENT", LocalDate.now(), "Male", "DELIVERY AGENT", "abcd4");
			when(profileRepository.findByMobileNumber(any(Long.class))).thenReturn(user);
			UserProfile profile = profileService.findByMobileNumber(Long.parseLong("9097834526"));
			ReflectionAssert.assertReflectionEquals(user, profile);
		}
		
	}
	
	@Nested
	@DisplayName("Get by user name")
	class GetByUserNameMethod{
		
		@Test
		@DisplayName("Verification")
		void testGetByUserName1() {
			InOrder inOrder = inOrder(profileRepository);
			profileService.getByUserName("Harry Potter");
			inOrder.verify(profileRepository, times(1)).findByFullName(any(String.class));
		}
		
		@Test
		@DisplayName("Getting by user name")
		void testGetByUserName2() {
			Address address = new Address(202, null, null, "Bangalore", "Karnataka", 560076);
			List<Address> addresses = new ArrayList<Address>();
			addresses.add(address);
			UserProfile user = new UserProfile(101, "Harry Potter", "img", "harry@123.gmail.com",(Long) Long.parseLong("9097834526"), addresses, "I am a DELIVERY AGENT", LocalDate.now(), "Male", "DELIVERY AGENT", "abcd4");
			when(profileRepository.findByFullName(any(String.class))).thenReturn(user);
			UserProfile profile = profileService.getByUserName("Harry Potter");
			ReflectionAssert.assertReflectionEquals(user, profile);
		}
	}

}
