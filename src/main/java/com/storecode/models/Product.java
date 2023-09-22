package com.storecode.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long id;
	
	@Column(name = "product_name")
	private String name;
	
	@Column(name = "product_description")
	private String description;

	@Column(name = "product_stock")
	private int stock;

	@Column(name = "product_price")
	private double price;
	
	@Column(name = "img_url")
	private String img;

	@ManyToOne
	@JoinColumn(name = "product_provider")
	private Provider provider;
	
	@ManyToOne
	@JoinColumn(name = "product_category")
	private Category category;

	public Product() {
		super();
	}

	public Product(long id, String name, String ddescription, int stock, double price, Provider provider,
			Category category,String img) {
		super();
		this.id = id;
		this.name = name;
		this.description = ddescription;
		this.stock = stock;
		this.price = price;
		this.provider = provider;
		this.category = category;
		this.img = img;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String ddescription) {
		this.description = ddescription;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	

}
