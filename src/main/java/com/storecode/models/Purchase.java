package com.storecode.models;

import jakarta.persistence.*;

@Entity(name = "purchases")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_id")
	private long id;
	
	@Column(name = "purchase_status")
	private String status;
	
	@Column(name = "purchase_date")
	private String date;
	
	@Column(name = "purchase_total_value" )
	private double totalValue;
	
	@ManyToOne
	@JoinColumn(name = "purchase_user")
	User user;
	
	public Purchase() {
		super();
	}

	public Purchase(long id, String status, String date, double totalValue, User user) {
		this.id = id;
		this.status = status;
		this.date = date;
		this.totalValue = totalValue;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
