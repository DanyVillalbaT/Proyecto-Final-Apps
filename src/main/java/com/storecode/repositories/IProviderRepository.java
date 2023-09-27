package com.storecode.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storecode.models.Product;
import com.storecode.models.Provider;

public interface IProviderRepository extends JpaRepository<Provider, Integer>{
	
	//List to find the products by the provider
	List<Product> findByProvider(Provider provider);

}
