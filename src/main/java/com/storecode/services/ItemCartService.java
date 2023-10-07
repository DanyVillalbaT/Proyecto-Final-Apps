package com.storecode.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storecode.models.ItemCart;
import com.storecode.models.Product;
import com.storecode.models.ShoppingCart;
import com.storecode.repositories.IItemCartRepository;

@Service
public class ItemCartService {
	
	@Autowired
	private IItemCartRepository itemCartRepository;
	
	public ItemCart getById(Long id) {
	    return itemCartRepository.findById(id).orElse(null);
	}
	
	public ItemCart save(ItemCart itemCart) {
		return itemCartRepository.save(itemCart);
	}
	
	public void deleteById(Long id) {
		itemCartRepository.deleteById(id);
	}
	
	public boolean existsByShoppingCartAndProduct(ShoppingCart shoppingCart, Product product) {
		return itemCartRepository.existsByShoppingCartAndProduct(shoppingCart, product);
	}
	
	public List<ItemCart> getByShoppingCart(ShoppingCart shoppingCart){
		return itemCartRepository.getByShoppingCart(shoppingCart);
	}

}
