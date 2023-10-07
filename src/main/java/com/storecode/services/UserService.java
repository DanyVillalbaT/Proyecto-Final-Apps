package com.storecode.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.storecode.models.Category;
import com.storecode.models.Product;
import com.storecode.models.User;
import com.storecode.repositories.IUserRepository;

@Service
public class UserService {
	@Autowired
	private IUserRepository userRepository;

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User getByiId(long id) {
		System.out.println(userRepository.findById(id).orElse(null));
		return userRepository.findById(id).orElse(null);
	}

	public User save(User producto) {
		return userRepository.save(producto);
	}

	public void delete(long id) {
		userRepository.deleteById(id);
	}
	
	public User getUserByEmailAndPassword( String email, String password){
		System.out.println( userRepository.findByEmailAndPassword(email, password) );
		return userRepository.findByEmailAndPassword(email, password);
	}
}
