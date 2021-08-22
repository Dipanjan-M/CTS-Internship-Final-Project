package com.cts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.models.entityModels.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	Optional<Category> findById(int id);
	
	Optional<Category> findByCategoryName(String name);

}
