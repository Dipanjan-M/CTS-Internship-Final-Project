package com.cts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.exception.ApiException;
import com.cts.models.entityModels.Category;
import com.cts.models.entityModels.Product;
import com.cts.models.requestModels.ProductRequest;
import com.cts.repository.CategoryRepository;
import com.cts.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	public Product createNewProduct(ProductRequest createReq, int categoryId) throws ApiException {
		Optional<Product> doesExists = productRepo.findByProductName(createReq.getProductName());
		if (doesExists.isPresent()) {
			throw new ApiException("Product with given name has already been added into inventory");
		}

		Category category = categoryRepo.findById(categoryId).get();

		Product product = new Product();
		product.setProductName(createReq.getProductName());
		product.setQuantity(createReq.getQuantity());
		product.setMrp(createReq.getMrp());
		product.setMfd(createReq.getMfd());
		product.setExp(createReq.getExp());
		product.setCategory(category);

		return productRepo.save(product);

	}

	public List<Product> fetchAllProducts() throws ApiException {
		List<Product> products = productRepo.findAll();
		if (products.isEmpty()) {
			throw new ApiException("There is no product in the inventory now.");
		}
		return products;
	}

	public Product updateExistingProduct(Product updateReq, int categoryId) throws ApiException {
		Optional<Product> existing = productRepo.findById(updateReq.getId());
		existing.orElseThrow(() -> new ApiException("Product with requested id wasn't found in inventory."));

		Product toUpdate = existing.get();
		toUpdate.setProductName(updateReq.getProductName());
		toUpdate.setQuantity(updateReq.getQuantity());
		toUpdate.setMrp(updateReq.getMrp());
		toUpdate.setMfd(updateReq.getMfd());
		toUpdate.setExp(updateReq.getExp());
		Category category = categoryRepo.findById(categoryId).get();
		toUpdate.setCategory(category);
		return productRepo.save(toUpdate);
	}
	
	public Product deleteExistingProduct(int productId) throws ApiException {
		Optional<Product> existing = productRepo.findById(productId);
		existing.orElseThrow(() -> new ApiException("Requested product doesn't exists"));
		
		productRepo.delete(existing.get());
		return existing.get();
	}

}
