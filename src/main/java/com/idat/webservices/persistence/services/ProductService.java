package com.idat.webservices.persistence.services;

import java.util.List;
import java.util.Optional;

import com.idat.webservices.persistence.models.Product;
import com.idat.webservices.persistence.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IService<Product> {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    public Optional<Product> findById(int id) {
        return null;
    }

    @Override
    public Optional<Product> findById(String barcode) {
        return repository.findByBarcode(barcode);
    }

    @Override
    public Optional<Product> save(Product entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    public Optional<Product> update(Product entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    public void delete(Product entity) {
        repository.delete(entity);

    }

}
