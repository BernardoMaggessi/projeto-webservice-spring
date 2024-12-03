package com.maggessibernardo.webserviceproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maggessibernardo.webserviceproject.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
