package com.cts.models.entityModels;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Component
@Entity
@Table(name = "category")
public class Category {

	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "category_name", nullable = false, length = 50, unique = true)
	private String categoryName;

	@Column(name = "tax_slab", nullable = false)
	private float taxSlab;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	private Set<Product> productsList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public float getTaxSlab() {
		return taxSlab;
	}

	public void setTaxSlab(float taxSlab) {
		this.taxSlab = taxSlab;
	}

	public Set<Product> getProductsList() {
		return productsList;
	}

	public void setProductsList(Set<Product> productsList) {
		this.productsList = productsList;
	}

}
