package com.storecode.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storecode.models.ShoppingCart;
import com.storecode.models.User;
import com.storecode.repositories.IShoppingCartRepository;

@Service
public class ShoppingCartService {
	
	@Autowired
	private IShoppingCartRepository shoppingCartRepository;
	
	public ShoppingCart getById(Long id) {
	    return shoppingCartRepository.findById(id).orElse(null);
	}
	
	public ShoppingCart save(ShoppingCart shoppingCart) {
		return shoppingCartRepository.save(shoppingCart);
	}
	
	public void deleteById(Long id) {
		shoppingCartRepository.deleteById(id);
	}
	
	public ShoppingCart existsByUser(User user) {
		return shoppingCartRepository.existsByUser(user);
	}

}
