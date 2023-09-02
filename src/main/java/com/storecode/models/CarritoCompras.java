package com.storecode.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CarritoCompras {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private List<ItemCarrito> listaItemsCarrito;
	private double valorTotalItems;
	//private long idUser; Esta es la fk entre usuario y el carrito

	public CarritoCompras() {
		super();
	}
	
	public CarritoCompras(long id, double valorTotalItems) { //Faltan parámetros
		super();
		this.id = id;
		this.valorTotalItems = calcularValorTotalItems();
		this.listaItemsCarrito = new ArrayList<>();
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

	public List<ItemCarrito> getListaItemsCarrito (){
		return listaItemsCarrito;
	}
	
	public double calcularValorTotalItems() {
		valorTotalItems = 0;
		//Aquí va todo el proceso para hallar el cálculo
		return valorTotalItems;
	}
	
}
