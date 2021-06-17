package com.idat.webservices.persistence.services;

import java.util.List;
import java.util.Optional;

import com.idat.webservices.persistence.models.DetailRequest;
import com.idat.webservices.persistence.repositories.DetailRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailRequestService implements IService<DetailRequest> {

    @Autowired
    private DetailRequestRepository repository;

    @Override
    public List<DetailRequest> findAll() {
        return (List<DetailRequest>) repository.findAll();
    }

    @Override
    public Optional<DetailRequest> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Optional<DetailRequest> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<DetailRequest> save(DetailRequest entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    public Optional<DetailRequest> update(DetailRequest entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    public void delete(DetailRequest entity) {
        repository.delete(entity);
    }

}
