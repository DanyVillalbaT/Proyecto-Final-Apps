package com.storecode.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity(name = "providers")
public class Provider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prov_id")
	private long id;

	@NotBlank(message = "El nombre del proveedor es obligatorio")
	@Column(name = "prov_name")
	private String name;
	
	@NotBlank(message = "La dirección del proveedor es obligatoria")
	@Column(name = "prov_ubication")
	private String ubication;
	
	@NotBlank(message = "El teléfono es obligatorio")
	@Size(min= 10, max=10, message="El teléfono debe tener 10 caracteres")
	@Column(name = "prov_phone", columnDefinition = "char(10)")
	private String phone;
	

	
	public Provider() {
		super();
	}

	public Provider(long id, @NotBlank(message = "El nombre del proveedor es obligatorio")String name, 
			@NotBlank(message = "La dirección del proveedor es obligatoria") String ubication, 
			@NotBlank(message = "El teléfono es obligatorio")
			@Size(min= 10, max=10, message="El teléfono debe tener 10 caracteres")String phone) {
		super();
		this.id = id;
		this.name = name;
		this.ubication = ubication;
		this.phone = phone;
		
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

	public String getUbication() {
		return ubication;
	}

	public void setUbication(String ubication) {
		this.ubication = ubication;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
}