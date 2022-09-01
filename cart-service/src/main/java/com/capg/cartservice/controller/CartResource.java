package com.capg.cartservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.cartservice.model.Cart;
import com.capg.cartservice.model.Item;
import com.capg.cartservice.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartResource {
	
	@Autowired
	private CartService service;
	
	@GetMapping("/{cartId}")
	public Cart getCartById(@PathVariable(value = "cartId") Integer id){
		return service.getCartById(id);
	}
	
	@GetMapping("/{cartId}/items")
	public List<Item> getAllItems(@PathVariable(value = "cartId") Integer id){
		return service.getAllItems(id);
	}
	
	@PostMapping("/{cartId}")
	public Cart addToCart(@PathVariable(value = "cartId") Integer id,@RequestBody Item item){
		return service.addToCart(id,item);
	}
	
	@PutMapping("/{cartId}")
	public void updateInCart(@PathVariable(value = "cartId") Integer id,@RequestBody Item item){
		service.updateInCart(id,item);
	}
	
	@DeleteMapping("/{cartId}")
	public void deleteFromCart(@PathVariable(value = "cartId") Integer id,@RequestBody Item item){
		service.deleteFromCart(id,item.getItemId());
	}


}
