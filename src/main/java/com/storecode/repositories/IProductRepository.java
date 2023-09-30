package com.storecode.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storecode.models.Category;
import com.storecode.models.Product;
import com.storecode.models.Provider;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{
	

	List<Product> findByCategory(Category category);

	boolean existsByCategory(Category category);

	boolean existsByProvider(Provider provider);

}
