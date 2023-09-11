package com.storecode.models;

import java.util.List;

public class PurchaseDetail {
	
	private long id;
	private List<ItemCart> itemsList;
	private double accumulatedValue;
	private String deliveryAddress;
	private String paymentMethod;
	
	public PurchaseDetail() {
		super();
	}

	public PurchaseDetail(long id, List<ItemCart> itemsList, double accumulatedValue, String deliveryAddress,
			String paymentMethod) {
		super();
		this.id = id;
		this.itemsList = itemsList;
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

	public List<ItemCart> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<ItemCart> itemsList) {
		this.itemsList = itemsList;
	}

	public double getAccumulatedValue() {
		return accumulatedValue;
	}

	public void setAccumulatedValue(double accumulatedValue) {
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

}
