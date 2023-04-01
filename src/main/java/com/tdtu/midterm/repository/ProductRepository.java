package com.tdtu.midterm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tdtu.midterm.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	List<Product> findByNameContainingIgnoreCase(@Param("key") String key);
	
	@Query("SELECT p FROM Product p WHERE CONCAT(p.category, '') LIKE %:category% AND CONCAT(p.brand, '') LIKE %:brand% AND p.price >= :bottom AND p.price <= :top")
	List<Product> filter(@Param("category") String category, @Param("brand") String brand, @Param("bottom") int bottom, @Param("top") int top);
}
