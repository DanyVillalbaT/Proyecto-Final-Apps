package com.storecode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storecode.models.ShoppingCart;
import com.storecode.models.User;

public interface IShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{
	
	ShoppingCart existsByUser(User user);

}
