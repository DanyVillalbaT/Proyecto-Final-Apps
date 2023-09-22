package com.storecode.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity(name = "sales")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sale_id")
	private long id;

	@Column(name = "sale_status")
	private String status;
	
	@Column(name = "sale_date")
	private String date;
	
	@Column(name = "sale_total_value")
	private double totalValue;
	
	@Transient
	private SaleDetail saleDetail;
	
	@OneToOne
	@JoinColumn(name = "sale_user")
	User user;
	
	public Sale() {
		super();
	}

	public Sale(long id, String status, String date, double totalValue, SaleDetail saleDetail, User user) {
		super();
		this.id = id;
		this.status = status;
		this.date = date;
		this.totalValue = calculateTotalValue();
		this.saleDetail = saleDetail;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	public SaleDetail getSaleDetail() {
		return saleDetail;
	}

	public void setSaleDetail(SaleDetail saleDetail) {
		this.saleDetail = saleDetail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public double calculateTotalValue() {
		double tax = 0;
		double deliveryCost = 0;
		return totalValue = saleDetail.getAccumulatedValue() + tax + deliveryCost;
	}
	
}
