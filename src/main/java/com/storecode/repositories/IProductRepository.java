package com.storecode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storecode.models.Category;
import com.storecode.models.Product;
import com.storecode.models.Provider;

public interface IProductRepository extends JpaRepository<Product, Long>{
	
	boolean existsByCategory(Category category);

	boolean existsByProvider(Provider provider);
}
