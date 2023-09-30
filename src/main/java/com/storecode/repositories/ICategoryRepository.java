package com.storecode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storecode.models.Category;
import java.util.List;



public interface ICategoryRepository extends JpaRepository<Category, Long> {
	
	//public List<Category> listaCategory();

	
}
