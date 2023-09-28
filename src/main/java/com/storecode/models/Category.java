package com.storecode.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cate_id")
	private long id;
	
	@Column(name = "cate_name")
	private String name;
	
	public Category() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Category(long id, String name) {
		super();
		this.id = id;
		this.name = name;
		
	}

	public long getId() {
		return this.id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}



}
