package com.cts.models.entityModels;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Component
@Entity
@Table(name = "products")
public class Product {

	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "product_name", nullable = false, length = 50, unique = true)
	private String productName;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "mrp")
	private float mrp;

	@Column(name = "mfd")
	private Date mfd;

	@Column(name = "exp")
	private Date exp;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	private Category category;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getMrp() {
		return mrp;
	}

	public void setMrp(float mrp) {
		this.mrp = mrp;
	}

	public Date getMfd() {
		return mfd;
	}

	public void setMfd(Date mfd) {
		this.mfd = mfd;
	}

	public Date getExp() {
		return exp;
	}

	public void setExp(Date exp) {
		this.exp = exp;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getCategoryId() {
		return this.category.getId();
	}

	public String getCategoryName() {
		return this.category.getCategoryName();
	}

	public float getTaxSlab() {
		return this.category.getTaxSlab();
	}

}
