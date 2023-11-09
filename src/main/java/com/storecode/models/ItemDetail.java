package com.storecode.models;

import jakarta.persistence.*;

@Entity(name = "item_details")
public class ItemDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private long id;

	@ManyToOne
	@JoinColumn(name = "item_product")
	Product product;

	@Column(name = "item_quantity")
	private int quantityItems;

	@Column(name = "item_accumulated_value")
	private int accumulatedValue;

	@ManyToOne
	@JoinColumn(name = "item_purchase_detail")
	private PurchaseDetail purchaseDetail;

	public ItemDetail() {
		super();
	}

	public ItemDetail(long id, Product product, int quantityItems, int accumulatedValue, PurchaseDetail purchaseDetail) {
		this.id = id;
		this.product = product;
		this.quantityItems = quantityItems;
		this.accumulatedValue = accumulatedValue;
		this.purchaseDetail = purchaseDetail;
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

	public PurchaseDetail getPurchaseDetail() {
		return purchaseDetail;
	}

	public void setPurchaseDetail(PurchaseDetail purchaseDetail) {
		this.purchaseDetail = purchaseDetail;
	}
}
