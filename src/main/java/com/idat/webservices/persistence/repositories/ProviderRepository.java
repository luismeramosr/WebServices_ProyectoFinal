package com.idat.webservices.persistence.repositories;

import java.util.Optional;

import com.idat.webservices.persistence.models.Provider;

import org.springframework.data.repository.CrudRepository;

public interface ProviderRepository extends CrudRepository<Provider, Integer> {
	public Optional<Provider> findByName(String name);
}
