package com.idat.webservices.persistence.repositories;

import java.util.Optional;

import com.idat.webservices.persistence.models.Request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    public Optional<Request> findById(int id);
    
}
