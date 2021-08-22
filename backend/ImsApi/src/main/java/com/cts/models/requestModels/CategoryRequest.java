package com.cts.models.requestModels;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryRequest {

	@NotNull(message = "Category name must not be null")
	@NotBlank(message = "Category name must not be blank")
	@NotEmpty(message = "Category name must not be empty")
	@Size(min = 3, max = 50, message = "Category name should be in between 3 to 50 character")
	private String categoryName;

	@NotNull(message = "Tax slab must not be null")
	@NotEmpty(message = "Tax slab must not be empty")
	private float taxSlab;

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

}
