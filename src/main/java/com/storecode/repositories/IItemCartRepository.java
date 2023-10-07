package com.storecode.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storecode.models.ItemCart;
import com.storecode.models.Product;
import com.storecode.models.ShoppingCart;

public interface IItemCartRepository extends JpaRepository<ItemCart, Long>{
	
	boolean existsByShoppingCartAndProduct(ShoppingCart shoppingCart, Product product);
	
	List<ItemCart> getByShoppingCart(ShoppingCart shoppingCart);

}
