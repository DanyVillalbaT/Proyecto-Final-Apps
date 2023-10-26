package com.storecode.models;

import java.util.List;


public class PurchaseDetail {
	
	private long id;
	private int accumulatedValue;
	private String deliveryAddress;
	private String paymentMethod;
	private List<ItemCart> itemsCart;
	
	public PurchaseDetail() {
		super();
	}

	public PurchaseDetail(long id, int accumulatedValue, String deliveryAddress,
			String paymentMethod) {
		super();
		this.id = id;
		
		this.accumulatedValue = accumulatedValue;
		this.deliveryAddress = deliveryAddress;
		this.paymentMethod = paymentMethod;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAccumulatedValue() {
		return accumulatedValue;
	}

	public void setAccumulatedValue(int accumulatedValue) {
		this.accumulatedValue = accumulatedValue;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<ItemCart> getItemsCart() { return itemsCart; }

	public void setItemsCart(List<ItemCart> itemsCart) { this.itemsCart = itemsCart; }
}
