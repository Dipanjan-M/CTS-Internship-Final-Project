package com.cts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.exception.ApiException;
import com.cts.models.requestModels.CategoryRequest;
import com.cts.models.responseModels.ApiResponse;
import com.cts.services.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/category/get-all-categories")
	public ResponseEntity<?> getAllCategories() throws ApiException {
		return ResponseEntity.ok(new ApiResponse(200, "Fetched all categories", categoryService.fetchAllCategories()));
	}

	@PostMapping("/category/create-category")
	public ResponseEntity<?> createCategory(@RequestBody CategoryRequest createRequest) throws ApiException {
		return ResponseEntity.ok(new ApiResponse(200, "New category has been created successfully",
				categoryService.createNewCategory(createRequest)));
	}

	@PutMapping("/category/update-category/{categoryId}")
	public ResponseEntity<?> updateCategory(@RequestBody CategoryRequest updateRequest, @PathVariable int categoryId)
			throws ApiException {
		return ResponseEntity.ok(new ApiResponse(200, "Successfully updated",
				categoryService.updateExistingCategory(updateRequest, categoryId)));
	}

	@DeleteMapping("/category/delete/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable int categoryId) throws ApiException {
		return ResponseEntity.ok(new ApiResponse(200, "Category deleted successfully",
				categoryService.deleteExistingCategory(categoryId)));
	}

}
