package com.storecode.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ItemCarrito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//private Producto producto;
	private int cantidadEscogida;
	private double valorAcumulado;
	
	public ItemCarrito() {
		super();
	}

	public ItemCarrito(long id, int cantidadEscogida) {
		super();
		this.id = id;
		this.cantidadEscogida = cantidadEscogida;
		this.valorAcumulado = calcularValorAcumulado();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCantidadEscogida() {
		return cantidadEscogida;
	}

	public void setCantidadEscogida(int cantidadEscogida) {
		this.cantidadEscogida = cantidadEscogida;
	}

	public double getValorAcumulado() {
		return valorAcumulado;
	}

	public void setValorAcumulado(double valorAcumulado) {
		this.valorAcumulado = valorAcumulado;
	}

	public double calcularValorAcumulado() {
		double valorAcumulado = 0;
		//Aquí va el proceso para hallar el cálculo
		return valorAcumulado;
	}

}
