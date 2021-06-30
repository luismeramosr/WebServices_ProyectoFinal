package com.idat.webservices.api.controllers;

import java.util.List;

import com.idat.webservices.persistence.models.Product;
import com.idat.webservices.persistence.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    @Operation(operationId = "getAllProducts")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(service.findAll(),HttpStatus.OK);
    }
    
}
