package com.storecode.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	private double accumulatedValue;
	
	public ItemCart() {
		super();
	}

	public ItemCart(long id, Producto product, int quantityItems, double accumulatedValue) {
		super();
		this.id = id;
		this.product = product;
		this.quantityItems = quantityItems;
		this.accumulatedValue = calculateAccumulatedValue();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Producto getProduct() {
		return product;
	}

	public void setProduct(Producto product) {
		this.product = product;
	}

	public int getQuantityItems() {
		return quantityItems;
	}

	public void setQuantityItems(int quantityItems) {
		this.quantityItems = quantityItems;
	}

	public double getAccumulatedValue() {
		return accumulatedValue;
	}

	public void setAccumulatedValue(double accumulatedValue) {
		this.accumulatedValue = accumulatedValue;
	}

	public double calculateAccumulatedValue() {
		return accumulatedValue = quantityItems * product.getPrecioActual();
	}

}
