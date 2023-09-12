package com.storecode.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombre;
	private String descripcion;
	private int cantDisponible;
	private double precioActual;

	public Product(long id, String nombre, String descripcion, int cantDisponible, double precioActual) {
		// TODO Auto-generated constructor stub
		this.cantDisponible = cantDisponible;
		this.descripcion = descripcion;
		this.id = id;
		this.nombre = nombre;
		this.precioActual = precioActual;
	}

	public Product() {
		super();
	}

	public int getCantDisponible() {
		return cantDisponible;
	}

	public String getDescripcion() {
		return descripcion;

	}
	public long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public double getPrecioActual() {
		return precioActual;
	}
	public void setCantDisponible(int cantDisponible) {
		this.cantDisponible = cantDisponible;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setPrecioActual(double precioActual) {
		this.precioActual = precioActual;
	}
}
