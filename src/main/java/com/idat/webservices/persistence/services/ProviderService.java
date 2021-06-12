package com.idat.webservices.persistence.services;

import java.util.List;
import java.util.Optional;

import com.idat.webservices.persistence.models.Provider;
import com.idat.webservices.persistence.repositories.ProviderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderService implements IService<Provider> {

	@Autowired
	private ProviderRepository repository;

	@Override
	public List<Provider> findAll() {
		return (List<Provider>) repository.findAll();
	}

	@Override
	public Optional<Provider> findById(int id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Provider> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Provider> save(Provider entity) {
		return Optional.of(repository.save(entity));
	}

	@Override
	public Optional<Provider> update(Provider entity) {
		return Optional.of(repository.save(entity));
	}

	@Override
	public void delete(Provider entity) {
		repository.delete(entity);	
	}
	
}
