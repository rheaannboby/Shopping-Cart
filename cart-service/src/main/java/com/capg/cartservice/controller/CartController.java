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
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/{cartId}")
	public Cart getCartById(@PathVariable(value = "cartId") Integer id){
		return cartService.getCartById(id);
	}
	
	@GetMapping("/{cartId}/items")
	public List<Item> getAllItems(@PathVariable(value = "cartId") Integer id){
		return cartService.getAllItems(id);
	}
	
	@PostMapping("/{cartId}")
	public Cart addToCart(@PathVariable(value = "cartId") Integer id,@RequestBody Item item){
		return cartService.addToCart(id,item);
	}
	
	@PutMapping("/{cartId}")
	public void updateInCart(@PathVariable(value = "cartId") Integer id,@RequestBody Item item){
		cartService.updateInCart(id,item);
	}
	
	@DeleteMapping("/{cartId}")
	public void deleteFromCart(@PathVariable(value = "cartId") Integer id,@RequestBody Item item){
		cartService.deleteFromCart(id,item.getItemId());
	}


}
