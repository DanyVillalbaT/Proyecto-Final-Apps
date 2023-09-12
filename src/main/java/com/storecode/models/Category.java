package com.storecode.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombre;
	public Category(long id,String nombre) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.nombre = nombre;
	}
	public Category() {
		// TODO Auto-generated constructor stub
		super();
	}
	public long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
