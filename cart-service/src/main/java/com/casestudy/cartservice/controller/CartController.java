package com.casestudy.cartservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.casestudy.cartservice.model.Cart;
import com.casestudy.cartservice.model.Item;
import com.casestudy.cartservice.model.Product;
import com.casestudy.cartservice.service.CartService;

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
				item.setImage(product.getImage());
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
	
	@DeleteMapping("/{cartId}/{itemId}")
	public void deleteFromCart(@PathVariable(value = "cartId") Integer id,@PathVariable(value = "itemId") Integer itemId){
		Item item = new Item();
		System.err.println("Delete : " +itemId);
		Product product = restTemplate.getForObject("http://PRODUCT-SERVICE/product/id/"+itemId, Product.class);
		item.setItemId(itemId);
		item.setPrice(product.getPrice());
		cartService.deleteFromCart(id,item);
	}
	
	@DeleteMapping("/all/{cartId}")
	public void clearCart(@PathVariable(value = "cartId") Integer id){
		cartService.clearCart(id);
	}


}
