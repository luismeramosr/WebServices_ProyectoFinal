package com.idat.webservices.persistence.repositories;

import java.util.Optional;

import com.idat.webservices.persistence.models.Request;

import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Integer> {
    public Optional<Request> findById(int id);
    
}
