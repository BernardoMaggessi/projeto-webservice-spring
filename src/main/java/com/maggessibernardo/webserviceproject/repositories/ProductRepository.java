package com.maggessibernardo.webserviceproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maggessibernardo.webserviceproject.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	
}
