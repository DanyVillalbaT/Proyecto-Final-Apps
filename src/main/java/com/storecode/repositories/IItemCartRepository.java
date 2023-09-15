package com.storecode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storecode.models.ItemCart;

public interface IItemCartRepository extends JpaRepository<ItemCart, Long>{

}
