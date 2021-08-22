package com.cts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.models.entityModels.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Optional<Product> findById(int id);
	
	Optional<Product> findByProductName(String productName);

}
