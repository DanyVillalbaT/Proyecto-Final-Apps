package com.storecode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storecode.models.ShoppingCart;

public interface IShoppingCart extends JpaRepository<ShoppingCart, Long>{

}
