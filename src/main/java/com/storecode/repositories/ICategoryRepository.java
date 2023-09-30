package com.storecode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.storecode.models.Category;


public interface ICategoryRepository extends JpaRepository<Category, Long> {

	
}
