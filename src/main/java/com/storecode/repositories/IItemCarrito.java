package com.storecode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storecode.models.ItemCarrito;

public interface IItemCarrito extends JpaRepository<ItemCarrito, Long>{

}
