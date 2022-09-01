package com.capg.cartservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capg.cartservice.model.Cart;
import com.capg.cartservice.model.Item;

@Service
public interface CartService {

	public abstract Cart getCartById(Integer id);
	public abstract List<Item> getAllItems(Integer id);
	public abstract Cart addToCart(Integer id,Item item);
	public abstract Cart updateInCart(Integer id,Item item);
	public abstract Cart deleteFromCart(Integer id,Integer itemId);
}
