package com.maggessibernardo.webserviceproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maggessibernardo.webserviceproject.entities.OrderItem;
import com.maggessibernardo.webserviceproject.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{
	

}