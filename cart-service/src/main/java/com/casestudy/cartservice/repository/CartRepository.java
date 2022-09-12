package com.casestudy.cartservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.cartservice.model.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, Integer>{
	public Cart findByCartId(Integer cartId);

}
