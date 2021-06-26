package com.idat.webservices.persistence.repositories;

import java.util.Optional;

import com.idat.webservices.persistence.models.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
	public Optional<Product> findByBarcode(String barcode);
}
