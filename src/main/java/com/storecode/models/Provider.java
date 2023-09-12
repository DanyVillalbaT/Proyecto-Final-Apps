package com.storecode.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;


@Entity
public class Provider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "prov_name")
	private String name;
	

	@Column(name = "prov_ubication")
	private String ubication;
	

	@Column(name = "prov_phone")
	private int phone;
	

	@Column(name = "prov_product_list")
	private List<Product> listaProductos;
	
	public Provider() {
		super();
	}

	public Provider(int id, String name, String ubication, int phone, List<Product> listaProductos) {
		super();
		this.id = id;
		this.name = name;
		this.ubication = ubication;
		this.phone = phone;
		this.listaProductos = listaProductos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUbication() {
		return ubication;
	}

	public void setUbication(String ubication) {
		this.ubication = ubication;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public List<Product> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Product> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	
}