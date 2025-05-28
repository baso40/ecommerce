package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
List<Product> findByCategory(Product.Category category);
    
	@Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findByNameContaining(@Param("name") String name);
    
    List<Product> findByStockQuantityGreaterThan(Integer quantity);

}
