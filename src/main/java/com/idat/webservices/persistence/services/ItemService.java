package com.idat.webservices.persistence.services;

import java.util.List;
import java.util.Optional;

import com.idat.webservices.persistence.models.Item;
import com.idat.webservices.persistence.repositories.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService implements IService<Item> {

    @Autowired
    private ItemRepository repository;

    @Override
    public List<Item> findAll() {
        return (List<Item>) repository.findAll();
    }

    @Override
    public Optional<Item> findById(int id) {
        return null;
    }

    @Override
    public Optional<Item> findById(String barcode) {
        return repository.findByBarcode(barcode);
    }

    @Override
    public Optional<Item> save(Item entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    public Optional<Item> update(Item entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    public void delete(Item entity) {
        repository.delete(entity);

    }

}
