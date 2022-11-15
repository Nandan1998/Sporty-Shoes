package com.shoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shoes.entity.Login;
import com.shoes.entity.Product;

public interface UserRepository extends JpaRepository<Login, Long> {
	
	@Query("SELECT u FROM Login u WHERE CONCAT(u.id, u.email, u.firstName, u.lastName) LIKE %?1%")
    List<Login> findByKeyword(String keyword);

}
