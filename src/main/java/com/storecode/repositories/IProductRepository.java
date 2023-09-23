package com.storecode.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.storecode.models.Product;

public interface IProductRepository extends JpaRepository<Product, Long>{

	@Transactional(readOnly = true)
	@Query("Select x from products where products.product_category = :param")
	public abstract List<Product> getProductsByCategory(@Param(value = "param") int  idCategory);
}
