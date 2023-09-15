package com.storecode.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "cate_name")
	private String name;
	
	@Column(name = "product_list")
	private List<Product> listaProductos;
	
	
	public Category() {
		// TODO Auto-generated constructor stub
		super();
	}


	public Category(long id, String name, List<Product> listaProductos) {
		super();
		this.id = id;
		this.name = name;
		this.listaProductos = listaProductos;
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


	public List<Product> getListaProductos() {
		return listaProductos;
	}


	public void setListaProductos(List<Product> listaProductos) {
		this.listaProductos = listaProductos;
	}

}
