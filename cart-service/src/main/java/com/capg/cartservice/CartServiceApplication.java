package com.capg.cartservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.capg.cartservice.model.Cart;
import com.capg.cartservice.repository.CartRepository;

@SpringBootApplication
@EnableEurekaClient
public class CartServiceApplication{

	public static void main(String[] args) {
		SpringApplication.run(CartServiceApplication.class, args);
	}

}

/*
 * public class CartServiceApplication implements CommandLineRunner{
	
	private CartRepository cartRepository;
	
	@Autowired
	public CartServiceApplication(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CartServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(cartRepository.findAll().isEmpty()) {
			cartRepository.save(new Cart(101,1200.0));
			cartRepository.save(new Cart(102,1200.0));
		}
		
		for(Cart cart : cartRepository.findAll()) {
			System.out.println(cart);
		}
		
	}

}
*/
