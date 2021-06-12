package com.idat.webservices.api.controllers;

import java.util.List;

import com.idat.webservices.persistence.models.Provider;
import com.idat.webservices.persistence.services.ProviderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("provider")
public class ProviderController {

	@Autowired
	private ProviderService service;
	
	@GetMapping("/public/all")
	@Operation(operationId = "getAllProviders")
	public ResponseEntity<List<Provider>> getAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

}
