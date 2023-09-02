package com.storecode.models;

import java.util.List;

public class DetalleVenta {
	
	private long id;
	private List<ItemCarrito> listaItems;
	private String direccionEnvio;
	private double costoEnvio;
	private double impuesto;
	private String tipoPago;
	
	public DetalleVenta() {
		super();
	}

	public DetalleVenta(long id, List<ItemCarrito> listaItems, String direccionEnvio, double costoEnvio, double impuesto, String tipoPago) {
		super();
		this.id = id;
		this.listaItems = listaItems;
		this.direccionEnvio = direccionEnvio;
		this.costoEnvio = costoEnvio;
		this.impuesto = impuesto;
		this.tipoPago = tipoPago;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<ItemCarrito> getListaItems() {
		return listaItems;
	}

	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public double getCostoEnvio() {
		return costoEnvio;
	}

	public void setCostoEnvio(double costoEnvio) {
		this.costoEnvio = costoEnvio;
	}

	public double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(double impuesto) {
		this.impuesto = impuesto;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

}
