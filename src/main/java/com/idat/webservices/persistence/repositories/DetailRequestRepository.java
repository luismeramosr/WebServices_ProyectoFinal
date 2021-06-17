package com.idat.webservices.persistence.repositories;

import java.util.Optional;

import com.idat.webservices.persistence.models.DetailRequest;

import org.springframework.data.repository.CrudRepository;

public interface DetailRequestRepository extends CrudRepository<DetailRequest, Integer> {
    public Optional<DetailRequest> findById(int id);
}
