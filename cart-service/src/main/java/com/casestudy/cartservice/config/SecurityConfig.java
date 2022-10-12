package com.casestudy.cartservice.config;


/*import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;*/

import com.casestudy.cartservice.security.UserDetailsServiceImpl;

public class SecurityConfig {
	
}
/*
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public UserDetailsService users() {
        return new UserDetailsServiceImpl();
    }
    	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.antMatchers("/cart/{cartId}").hasRole("CUSTOMER")
		.antMatchers("/cart/{cartId}/items").hasRole("CUSTOMER")
		.antMatchers(HttpMethod.POST,"/cart").hasRole("CUSTOMER")
		.antMatchers(HttpMethod.PUT,"/cart").hasRole("CUSTOMER")
		.antMatchers(HttpMethod.DELETE,"/cart").hasRole("CUSTOMER")
		.anyRequest().authenticated()
		.and()
		.formLogin().permitAll()
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login")
        .deleteCookies("JSESSIONID")
        .invalidateHttpSession(true)
        .and()
        .exceptionHandling()
        //.accessDeniedHandler(new CustomAccessDeniedHandler())
        .and()
        .httpBasic();
		
		http.authenticationProvider(daoAuthenticationProvider());
        return http.build();
    }
	
	@Bean 
	public DaoAuthenticationProvider daoAuthenticationProvider(){
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(users());
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception{
		return configuration.getAuthenticationManager();
	}
	
	

}
*/
