package com.storecode.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storecode.models.Product;
import com.storecode.models.Provider;

@Repository
public interface IProviderRepository extends JpaRepository<Provider, Long>{
	
	//List to find the products by the provider
	List<Product> findByProvider(Provider provider);

}
