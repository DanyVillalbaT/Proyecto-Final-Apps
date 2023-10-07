package com.storecode.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity(name = "item_carts")
public class ItemCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private long id;
	
	@OneToOne
	@JoinColumn(name = "item_product")
	Product product;
	
	@Column(name = "item_quantity")
	private int quantityItems;
	
	@Column(name = "item_accumulated_value")
	private int accumulatedValue;
	
	@OneToOne
	@JoinColumn(name = "item_cart_id")
	private ShoppingCart shoppingCart;
	
	public ItemCart() {
		super();
	}

	public ItemCart(long id, Product product, int quantityItems, int accumulatedValue, ShoppingCart shoppingCart) {
		super();
		this.id = id;
		this.product = product;
		this.quantityItems = quantityItems;
		this.accumulatedValue = accumulatedValue;
		this.shoppingCart = shoppingCart;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantityItems() {
		return quantityItems;
	}

	public void setQuantityItems(int quantityItems) {
		this.quantityItems = quantityItems;
	}

	public int getAccumulatedValue() {
		return accumulatedValue;
	}

	public void setAccumulatedValue(int accumulatedValue) {
		this.accumulatedValue = accumulatedValue;
	}

	public double calculateAccumulatedValue() {
		return accumulatedValue = quantityItems * product.getPrice();
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	

}
