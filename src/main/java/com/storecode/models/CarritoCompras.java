package com.storecode.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CarritoCompras {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//private List<ItemCarrito> listaItemsCarrito;
	private double valorTotalItems;
	//private long idUsuario o idPersona; Esta es la relación entre un usuario y su carrito

	public CarritoCompras() {
		super();
	}
	
	public CarritoCompras(long id, double valorTotalItems) { //Faltan parámetros
		super();
		this.id = id;
		this.valorTotalItems = calcularValorTotalItems();
	}
	
	//Faltan Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getValorTotalItems() {
		return valorTotalItems;
	}

	public void setValorTotalItems(double valorTotalItems) {
		this.valorTotalItems = valorTotalItems;
	}
	
	public double calcularValorTotalItems() {
		valorTotalItems = 0;
		//Aquí va todo el proceso para hallar el cálculo
		return valorTotalItems;
	}
	
}
