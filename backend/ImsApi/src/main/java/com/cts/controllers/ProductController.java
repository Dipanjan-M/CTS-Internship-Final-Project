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
import com.cts.models.entityModels.Product;
import com.cts.models.requestModels.ProductRequest;
import com.cts.models.responseModels.ApiResponse;
import com.cts.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/product/get-all-products")
	public ResponseEntity<?> getAllProducts() throws ApiException {
		return ResponseEntity
				.ok(new ApiResponse(200, "Fetched all products from inventory", productService.fetchAllProducts()));
	}

	@PostMapping("/product/add-product/{categoryId}")
	public ResponseEntity<?> createProduct(@RequestBody ProductRequest createReq, @PathVariable int categoryId)
			throws ApiException {
		return ResponseEntity.ok(new ApiResponse(200, "New product added successfully",
				productService.createNewProduct(createReq, categoryId)));
	}

	@PutMapping("/product/update-product/{categoryId}")
	public ResponseEntity<?> updateProduct(@RequestBody Product updateReq, @PathVariable int categoryId)
			throws ApiException {
		return ResponseEntity.ok(new ApiResponse(200, "Product updated successfully",
				productService.updateExistingProduct(updateReq, categoryId)));
	}

	@DeleteMapping("product/delete-product/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable int productId) throws ApiException {
		return ResponseEntity.ok(new ApiResponse(200, "Product has been deleted successfully",
				productService.deleteExistingProduct(productId)));
	}

}
