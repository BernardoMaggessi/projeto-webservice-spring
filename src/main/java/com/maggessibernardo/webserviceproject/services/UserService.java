package com.maggessibernardo.webserviceproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.maggessibernardo.webserviceproject.entities.User;
import com.maggessibernardo.webserviceproject.repositories.UserRepository;
import com.maggessibernardo.webserviceproject.services.execptions.DatabaseException;
import com.maggessibernardo.webserviceproject.services.execptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));//TRATAMENTO DE EXCEPTION PERSONALIZADA
	}
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
	    Optional<User> obj = repository.findById(id);
	    if (obj.isEmpty()) {
	        throw new ResourceNotFoundException(id); // Lançar exceção se não encontrar o usuário
	    }
	    try {
	        repository.deleteById(id);
	    } catch (DataIntegrityViolationException e) {
	        throw new DatabaseException(e.getMessage());
	    }
	}

	public User update(Long id, User obj) {
		try {
			User entity = repository.getReferenceById(id);
			updateData(entity,obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	private void updateData(User entity,User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
