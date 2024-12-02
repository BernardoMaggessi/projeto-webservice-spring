package com.maggessibernardo.webserviceproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maggessibernardo.webserviceproject.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
