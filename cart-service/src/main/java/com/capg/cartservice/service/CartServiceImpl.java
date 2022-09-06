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
			cart.setTotalPrice(0.0);
		}
		
		items.add(new Item(newItem));
		cart.setListOfItems(items);
		cart.setTotalPrice(cart.getTotalPrice()+(newItem.getPrice()*newItem.getQuantity()));
		Cart savedCart = cartRepository.save(cart);
		return savedCart;
	}

	@Override
	public Cart updateInCart(Integer id, Item item) {
		Cart cart = cartRepository.findByCartId(id);
		int oldQuantity = 0;
		for(Item itm : cart.getListOfItems()) {
			if(itm.getItemId().compareTo(item.getItemId())==0) {
				int index = cart.getListOfItems().indexOf(itm);
				oldQuantity = cart.getListOfItems().get(index).getQuantity();
				cart.getListOfItems().get(index).setQuantity(item.getQuantity());
				break;
			}
		}
		cart.setTotalPrice(cart.getTotalPrice() + (item.getQuantity() - oldQuantity)*item.getPrice());
		Cart upCart = cartRepository.save(cart);
		return upCart;
	}

	@Override
	public Cart deleteFromCart(Integer id,Item delItem) {
		Cart cart = cartRepository.findByCartId(id);
		int qty = 0;
		for(Item item : cart.getListOfItems()) {
			if(item.getItemId().compareTo(delItem.getItemId())==0) {
				int index = cart.getListOfItems().indexOf(item);
				qty = cart.getListOfItems().get(index).getQuantity();
				cart.getListOfItems().remove(item);
				break;
			}
		}
		cart.setTotalPrice(cart.getTotalPrice() - (qty*delItem.getPrice()));
		return cartRepository.save(cart);		
	}

	@Override
	public Cart clearCart(Integer id) {
		Cart cart = cartRepository.findByCartId(id);
		cart.getListOfItems().clear();
		cart.setTotalPrice(0.0);
		return cartRepository.save(cart);
	}

}
