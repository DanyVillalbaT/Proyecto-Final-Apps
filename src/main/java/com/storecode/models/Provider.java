package com.storecode.models;

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

	private String nombre;
	private String ubicacion;
	private int telefono;
	private List<Product> listaProductos;
	
	public Provider() {
		super();
	}
	
	public Provider(int id, String nombre, String ubicacion, int telefono, List<Product> listaProductos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.telefono = telefono;
		this.listaProductos = listaProductos;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public int getTelefono() {
		return telefono;
	}
	
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public List<Product> getListaProductos() {
		return listaProductos;
	}
	
	public void setListaProductos(List<Product> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	
}