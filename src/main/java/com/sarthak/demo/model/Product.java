package com.sarthak.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pId;
	private String pName;
	private Double price;
	
	@JsonIgnoreProperties("products")
	@ManyToOne(cascade = CascadeType.PERSIST)	
	@JoinColumn(name = "c_id", nullable=false)
	private Category category;
	
	public Product() {
		super();
	}

	public Product(int pId, String pName, Double price, Category category) {
		this.pId = pId;
		this.pName = pName;
		this.price = price;
		this.category = category;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [pId=" + pId + ", pName=" + pName + ", price=" + price + ", category=" + category + "]";
	}	
}