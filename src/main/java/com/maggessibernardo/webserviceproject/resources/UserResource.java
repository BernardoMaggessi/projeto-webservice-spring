package com.maggessibernardo.webserviceproject.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maggessibernardo.webserviceproject.entities.User;

//classe irá disponilibizar um recurso web para trabalhar com o objeto User

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<User> findAll(){
		User u = new User(1L,"Maria","Maria@gmail.com","21982610935","Orkutpmtgmt");
		return ResponseEntity.ok().body(u);
	}
}
