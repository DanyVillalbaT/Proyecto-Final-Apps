package com.storecode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storecode.models.Product;

public interface IProductRepository extends JpaRepository<Product, Long>{

}
