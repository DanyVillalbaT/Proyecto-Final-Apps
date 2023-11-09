package com.storecode.models;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "purchase_details")
public class PurchaseDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_detail_id")
	private long id;

	@Column(name = "purchase_detail_accumulated_value")
	private int accumulatedValue;

	@Column(name = "purchase_detail_iva")
	private int IVA;

	@Column(name = "purchase_detail_delivery_cost")
	private int deliveryCost;

	@Column(name = "purchase_detail_delivery_address")
	private String deliveryAddress;

	@Column(name = "purchase_detail_payment_method")
	private String paymentMethod;

	@OneToOne
	@JoinColumn(name = "purchase")
	private Purchase purchase;
	
	public PurchaseDetail() {
		super();
	}

	public PurchaseDetail(long id, int accumulatedValue, int IVA, int deliveryCost, String deliveryAddress, String paymentMethod, Purchase purchase) {
		this.id = id;
		this.accumulatedValue = accumulatedValue;
		this.IVA = IVA;
		this.deliveryCost = deliveryCost;
		this.deliveryAddress = deliveryAddress;
		this.paymentMethod = paymentMethod;
		this.purchase = purchase;
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

	public int getIVA() {
		return IVA;
	}

	public void setIVA(int IVA) {
		this.IVA = IVA;
	}

	public int getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(int deliveryCost) {
		this.deliveryCost = deliveryCost;
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

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
}
