package com.idat.webservices.persistence.services;

import java.util.List;
import java.util.Optional;

import com.idat.webservices.persistence.models.Role;
import com.idat.webservices.persistence.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IService<Role> {

    @Autowired
    private RoleRepository repository;

    @Override
    public List<Role> findAll() {
        return (List<Role>) repository.findAll();
    }

    @Override
    public Optional<Role> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Role> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Role> save(Role entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    public Optional<Role> update(Role entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    public void delete(Role entity) {
        repository.delete(entity);

    }

}
