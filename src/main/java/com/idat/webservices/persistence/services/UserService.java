package com.idat.webservices.persistence.services;

import java.util.List;
import java.util.Optional;

import com.idat.webservices.persistence.models.User;
import com.idat.webservices.persistence.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IService<User> {

	@Autowired
	private UserRepository repository;

	@Override
	public List<User> findAll() {
		return (List<User>) repository.findAll();
	}

	@Override
	public Optional<User> findById(int id) {
		return null;
	}

	@Override
	public Optional<User> findById(String id) {
		return repository.findById(id);
	}

	@Override
	public Optional<User> save(User entity) {
		return Optional.of(repository.save(entity));
	}

	@Override
	public Optional<User> update(User entity) {
		return Optional.of(repository.save(entity));
	}

	@Override
	public void delete(User entity) {
		repository.delete(entity);
	}

	public Optional<User> findByUsername(String username) {
		return repository.findByUsername(username);
	}
	
}
