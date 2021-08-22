package com.cts.models.requestModels;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ProductRequest {

	@NotNull(message = "Product name must not be null")
	@NotBlank(message = "Product name must not be blank")
	@NotEmpty(message = "Product name must not be empty")
	@Size(min = 4, max = 50, message = "Product name must be in between 4 to 50 characters")
	private String productName;

	@Positive(message = "Quantity must be a positive integer")
	private int quantity;

	@Min(value = 1L, message = "The MRP must be positive and greater than 0")
	private float mrp;

	private Date mfd;

	private Date exp;

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

}
