package com.maggessibernardo.webserviceproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maggessibernardo.webserviceproject.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
}
