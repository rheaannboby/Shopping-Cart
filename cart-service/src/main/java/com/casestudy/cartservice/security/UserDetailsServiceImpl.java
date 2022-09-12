package com.casestudy.cartservice.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.casestudy.cartservice.model.UserProfile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private ProfileRepository profileRepository;
	
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		  UserProfile retrievedUser = profileRepository.findByEmailId(username);
		  
		  if (retrievedUser == null) { throw new
		  UsernameNotFoundException("Could not find user"); }
		  
		  List<SimpleGrantedAuthority> authority = new ArrayList<>();
		  authority.add(new SimpleGrantedAuthority("ROLE_"+retrievedUser.getRole()));
		  User user = new User(retrievedUser.getEmailId(),passwordEncoder().encode(retrievedUser.getPassword()), authority);
		  
		  return user;
		 
		/*
		String pass = passwordEncoder().encode("pass");
		return new User("foo",pass, new ArrayList<>());
		*/
	}

}
