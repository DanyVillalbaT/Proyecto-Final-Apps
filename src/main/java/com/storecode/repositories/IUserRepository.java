package com.storecode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storecode.models.User;

public interface IUserRepository extends JpaRepository<User, Long>{

	User findByEmailAndPassword(String email, String password);
	
}