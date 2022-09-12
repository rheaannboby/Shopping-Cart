package com.casestudy.cartservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.casestudy.cartservice.model.Cart;
import com.casestudy.cartservice.model.Item;

@Service
public interface CartService {

	public abstract Cart getCartById(Integer id);
	public abstract List<Item> getAllItems(Integer id);
	public abstract Cart addToCart(Integer id,Item item);
	public abstract Cart updateInCart(Integer id,Item item);
	public abstract Cart deleteFromCart(Integer id,Item item);
	public abstract Cart clearCart(Integer id);
}
