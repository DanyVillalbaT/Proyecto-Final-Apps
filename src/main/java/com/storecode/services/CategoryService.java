package com.storecode.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storecode.models.Category;
import com.storecode.models.Product;
import com.storecode.repositories.ICategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private ICategoryRepository categoryRepository;

	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	public Category getByiId(Long id) {
		System.out.println(categoryRepository.findById(id).orElse(null));
		return categoryRepository.findById(id).orElse(null);
	}

	public Category save(Category categoria) {
		return categoryRepository.save(categoria);
	}

	public void delete(Long id) {
		categoryRepository.deleteById(id);
	}
	
}
