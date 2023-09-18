package com.storecode.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "shopping_carts")
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private long id;
	
	
	
	@Column(name = "cart_total_value")
	private double totalValueItems;
	
	@OneToOne
	@JoinColumn(name = "cart_user")
	User user;

	public ShoppingCart() {
		super();
	}
	
	public ShoppingCart(long id, double totalValueItems, User user) {
		super();
		this.id = id;
	
		this.totalValueItems = calculateTotalValueItems();
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public double getTotalValueItems() {
		return totalValueItems;
	}

	public void setTotalValueItems(double totalValueItems) {
		this.totalValueItems = totalValueItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double calculateTotalValueItems() {
		totalValueItems = 0;
		//Aquí va todo el proceso para hallar el cálculo
		return totalValueItems;
	}
	
}
