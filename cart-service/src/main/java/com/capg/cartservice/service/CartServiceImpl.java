package com.capg.cartservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capg.cartservice.model.Cart;
import com.capg.cartservice.model.Item;
import com.capg.cartservice.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public Cart getCartById(Integer id) {
		return cartRepository.findByCartId(id);
		
	}

	@Override
	public List<Item> getAllItems(Integer id) {
		return cartRepository.findByCartId(id).getListOfItems();
	}

	@Override
	public Cart addToCart(Integer id, Item newItem) {
		Cart cart;
		if(!cartRepository.existsById(id)) {
			cart = new Cart();
			cart.setCartId(id);
		}
		else {
			cart = cartRepository.findByCartId(id);
		}
		List<Item> items = cart.getListOfItems();
		if(items == null) {
			items = new ArrayList<Item>();
		}
		items.add(new Item(newItem));
		cart.setListOfItems(items);
		cart.setTotalPrice(cartTotal(cart));
		Cart savedCart = cartRepository.save(cart);
		return savedCart;
	}

	@Override
	public Cart updateInCart(Integer id, Item item) {
		Cart cart = cartRepository.findByCartId(id);
		for(Item itm : cart.getListOfItems()) {
			if(itm.getItemId().compareTo(item.getItemId())==0) {
				int index = cart.getListOfItems().indexOf(itm);
				cart.getListOfItems().get(index).setQuantity(item.getQuantity());
				break;
			}
		}
		cart.setTotalPrice(cartTotal(cart));
		Cart upCart = cartRepository.save(cart);
		return upCart;
	}

	@Override
	public Cart deleteFromCart(Integer id,Integer itemId) {
		Cart cart = cartRepository.findByCartId(id);
		for(Item item : cart.getListOfItems()) {
			if(item.getItemId().compareTo(itemId)==0) {
				cart.getListOfItems().remove(item);
				break;
			}
		}
		cart.setTotalPrice(cartTotal(cart));
		return cartRepository.save(cart);		
	}

	public double cartTotal(Cart cart) {
		return cart.getListOfItems().stream().map(item -> (item.getPrice()*item.getQuantity()))
				.collect(Collectors.summingDouble(Double::doubleValue));
	}


}
