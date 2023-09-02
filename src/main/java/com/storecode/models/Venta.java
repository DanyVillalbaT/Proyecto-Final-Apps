package com.storecode.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String estado;
	private String fecha;
	private double valorTotal;
	//private int idUsuario; Sería la fk de usuario
	private DetalleVenta detalleVenta;
	
	public Venta() {
		super();
	}
	
	public Venta(long id, String estado, String fecha, double valorTotal, DetalleVenta detalleVenta) {
		super();
		this.id = id;
		this.estado = estado;
		this.fecha = fecha;
		this.valorTotal = valorTotal;
		this.detalleVenta = detalleVenta;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public DetalleVenta getDetalleVenta() {
		return detalleVenta;
	}
	
}