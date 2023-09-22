package com.storecode.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storecode.models.Product;
import com.storecode.repositories.IProductRepository;

@Service
public class ProductService {
	@Autowired
	private IProductRepository productRepository;

	public List<Product> getAll() {
		return productRepository.findAll();
	}

	public Product getByiId(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	public Product save(Product producto) {
		return productRepository.save(producto);
	}

	public void delete(Long id) {
		productRepository.deleteById(id);
	}
}
