package com.storecode.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.storecode.models.Product;
import com.storecode.models.Provider;
import com.storecode.repositories.IProductRepository;
import com.storecode.repositories.IProviderRepository;

@Service
public class ProviderService {

	@Autowired
	private IProviderRepository providerRepository;
	
	@Autowired
	private IProductRepository productRepository;
	
	public List<Provider> getAll(){
		return providerRepository.findAll();
	}
	
	public Provider getById(Long id) {
	    return providerRepository.findById(id).orElse(null);
	}
	
	public Provider save(Provider provider) {
		return providerRepository.save(provider);
	}
	
	public void deleteById(Long id) {
		providerRepository.deleteById(id);
	}
	
	public boolean existsProductByProvider(Provider provider) {
		return productRepository.existsByProvider(provider);
	}
	
	/*public List<Product> getProductsByProvider(@PathVariable("idProvider") long idProvider){
		Provider provider = new Provider();
		provider.setId(idProvider);
		return providerRepository.findByProvider(provider);
	}*/

	
}
