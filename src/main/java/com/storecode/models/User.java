package com.storecode.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "users")
public class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;
	
	@Column(name = "user_name")
	private String name;
	
	@Column(name = "user_email")
	private String email;
	
	@Column(name = "user_password")
	private String password;
	
	@Column(name = "user_document")
	private String document;
	
	@Column(name = "user_address")
	private String address;
	
	@Column(name = "user_rol")
	private String rol;
	
	@Column(name = "user_telephone")
	private String telephone;
	
	@Column(name = "user_zip")
	private int zipCode;
	
	public User() {
		super();
	}

	public User(long id, String name, String email, String password, String document, String address, String rol,
			String telephone, int zipCode) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.document = document;
		this.address = address;
		this.rol = rol;
		this.telephone = telephone;
		this.zipCode = zipCode;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}	
	
}