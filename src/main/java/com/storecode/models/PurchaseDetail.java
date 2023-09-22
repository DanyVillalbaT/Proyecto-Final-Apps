package com.storecode.models;

import java.util.List;


public class PurchaseDetail {
	
	private long id;
	
	private double accumulatedValue;
	private String deliveryAddress;
	private String paymentMethod;
	
	public PurchaseDetail() {
		super();
	}

	public PurchaseDetail(long id, double accumulatedValue, String deliveryAddress,
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
