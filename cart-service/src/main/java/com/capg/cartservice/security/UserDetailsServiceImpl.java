package com.capg.cartservice.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.capg.cartservice.model.UserProfile;

import org.springframework.beans.factory.annotation.Autowired;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserProfile user = profileRepository.findByEmailId(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyUserDetails(user);
	}

}
