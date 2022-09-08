package com.capg.cartservice.security;

import java.util.*;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.capg.cartservice.model.UserProfile;

public class MyUserDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private UserProfile user;
	
	public MyUserDetails(UserProfile user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<String> roles = new HashSet<String>();
		roles.add("CUSTOMER");
		roles.add("MERCHANT");
		roles.add("DELIVERY AGENT");
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
         
        return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmailId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
