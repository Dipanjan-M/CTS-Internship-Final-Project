package com.cts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.exception.ApiException;
import com.cts.models.entityModels.Category;
import com.cts.models.requestModels.CategoryRequest;
import com.cts.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> fetchAllCategories() throws ApiException {
		List<Category> categories = categoryRepository.findAll();
		if (categories.isEmpty()) {
			throw new ApiException("No category has been found. Please add some categories.");
		}
		return categories;
	}

	public Category createNewCategory(CategoryRequest request) throws ApiException {

		Optional<Category> existingCategory = categoryRepository.findByCategoryName(request.getCategoryName());

		if (existingCategory.isPresent()) {
			throw new ApiException("Category with given name is already exists. Please use another name.");
		}

		Category category = new Category();

		category.setCategoryName(request.getCategoryName());
		category.setTaxSlab(request.getTaxSlab());

		return categoryRepository.save(category);
	}

	public Category updateExistingCategory(CategoryRequest updateReq, int id) throws ApiException {
		Optional<Category> existing = categoryRepository.findById(id);
		existing.orElseThrow(() -> new ApiException("No category found for the requested id"));

		Category category = existing.get();

		category.setCategoryName(updateReq.getCategoryName());
		category.setTaxSlab(updateReq.getTaxSlab());

		return categoryRepository.save(category);
	}

	public Category deleteExistingCategory(int id) throws ApiException {
		Optional<Category> existing = categoryRepository.findById(id);
		existing.orElseThrow(() -> new ApiException("No category found for the requested id"));

		categoryRepository.delete(existing.get());

		return existing.get();
	}

}
