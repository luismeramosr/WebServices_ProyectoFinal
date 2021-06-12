package com.idat.webservices.persistence.services;

import java.util.List;
import java.util.Optional;

public interface IService<T> {

	public List<T> findAll();

	public Optional<T> findById(int id);

	public Optional<T> findById(String id);

	public Optional<T> save(T entity);

	public Optional<T> update(T entity);

	public void delete(T entity);

}
