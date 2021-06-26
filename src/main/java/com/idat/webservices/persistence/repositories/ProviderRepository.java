package com.idat.webservices.persistence.repositories;

import java.util.Optional;

import com.idat.webservices.persistence.models.Provider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
	public Optional<Provider> findByName(String name);
}
