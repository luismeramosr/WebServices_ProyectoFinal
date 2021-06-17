package com.idat.webservices.persistence.services;

import java.util.List;
import java.util.Optional;

import com.idat.webservices.persistence.models.Request;
import com.idat.webservices.persistence.repositories.RequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService implements IService<Request> {

    @Autowired
    private RequestRepository repository;

    @Override
    public List<Request> findAll() {
        return (List<Request>) repository.findAll();
    }

    @Override
    public Optional<Request> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Request> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Request> save(Request entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    public Optional<Request> update(Request entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    public void delete(Request entity) {
        repository.delete(entity);

    }

}
