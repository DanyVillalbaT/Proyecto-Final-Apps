package com.storecode.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storecode.models.Provider;
import com.storecode.repositories.IProviderRepository;

@Service
public class ProviderService {

	@Autowired
	private IProviderRepository providerRepository;
	
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

	
}
