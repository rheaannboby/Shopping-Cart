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
import org.springframework.web.client.RestTemplate;

import com.capg.cartservice.model.Cart;
import com.capg.cartservice.model.Item;
import com.capg.cartservice.model.Product;
import com.capg.cartservice.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/{cartId}")
	public Cart getCartById(@PathVariable(value = "cartId") Integer id){
		Cart cart = cartService.getCartById(id);
		cart.getListOfItems().stream().forEach(item -> {
				Product product = restTemplate.getForObject("http://PRODUCT-SERVICE/product/id/"+item.getItemId(), Product.class);
				item.setProductName(product.getProductName());
				item.setPrice(product.getPrice());
		});
		return cart;
	}
	
	@GetMapping("/{cartId}/items")
	public List<Item> getAllItems(@PathVariable(value = "cartId") Integer id){
		List<Item> items = cartService.getAllItems(id);
		items.forEach(item -> {
				Product product = restTemplate.getForObject("http://PRODUCT-SERVICE/product/id/"+item.getItemId(), Product.class);
				item.setProductName(product.getProductName());
				item.setPrice(product.getPrice());
		});
		return items;
	}
	
	@PostMapping("/{cartId}")
	public Cart addToCart(@PathVariable(value = "cartId") Integer id,@RequestBody Item item){
		Product product = restTemplate.getForObject("http://PRODUCT-SERVICE/product/id/"+item.getItemId(), Product.class);
		item.setProductName(product.getProductName());
		item.setPrice(product.getPrice());
		return cartService.addToCart(id,item);
	}
	
	@PutMapping("/{cartId}")
	public void updateInCart(@PathVariable(value = "cartId") Integer id,@RequestBody Item item){
		Product product = restTemplate.getForObject("http://PRODUCT-SERVICE/product/id/"+item.getItemId(), Product.class);
		item.setPrice(product.getPrice());
		cartService.updateInCart(id,item);
	}
	
	@DeleteMapping("/{cartId}")
	public void deleteFromCart(@PathVariable(value = "cartId") Integer id,@RequestBody Item item){
		Product product = restTemplate.getForObject("http://PRODUCT-SERVICE/product/id/"+item.getItemId(), Product.class);
		item.setPrice(product.getPrice());
		cartService.deleteFromCart(id,item);
	}


}
