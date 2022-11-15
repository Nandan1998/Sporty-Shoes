package com.shoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shoes.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p WHERE CONCAT(p.productName, p.category, p.price, p.status, p.size) LIKE %?1%")
    List<Product> findByKeyword(String keyword);
	
	@Query("Select id from Product where category Like %?1%")
	List<Integer> getByCategory(String keyword);
}
