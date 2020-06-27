package com.springboot.ecommerce.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.ecommerce.entity.Product;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);  //
	
	//@Query(value = "select * from product where category_id = :id",nativeQuery = true)
	//Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
	
	
	
	
}
