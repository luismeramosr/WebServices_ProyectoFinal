package com.idat.webservices.api.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.idat.webservices.domain.dto.Response;
import com.idat.webservices.persistence.models.User;
import com.idat.webservices.persistence.services.UserService;
import com.idat.webservices.util.Console;
import com.idat.webservices.util.Encryption;
import com.idat.webservices.util.Helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService service;

	@Autowired
	Encryption encryption;

	@Autowired
	Helpers helpers;

	@GetMapping
	public ResponseEntity<Response<List<User>>> getAllUsers() {
		return new ResponseEntity<>(new Response<>(service.findAll()), HttpStatus.OK);
	}

	@GetMapping("operator")
	public ResponseEntity<Response<List<User>>> getAllOperators() {

		List<User> operators = service.findAll().stream()
			.filter(user -> user.getRole().getName().equals("Operario"))
			.collect(Collectors.toList());

		return new ResponseEntity<>(new Response<>(operators), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<Response<User>> getUserById(@PathVariable("id") String id) {

		User user = service.findById(id).orElse(null);

		if (user != null)
			return new ResponseEntity<>(new Response<>(user), HttpStatus.OK);
		else
			return new ResponseEntity<>(new Response<>(user, "No se encontró el usuario"), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Response<User>> addUser(@RequestBody User user) {
		Console.log(user.toString());
		String generatedUsername = String.format("%s%s", 
			user.getFirstName().substring(0, 2),
			String.valueOf(user.getDni()).substring(0,2));
		user.setId(helpers.generateId(service));
		user.setUsername(generatedUsername);
		user.setPassword(encryption.encryptPassword(generatedUsername));
		user.setActive(true);
		User u = service.save(user).orElse(null);

		if (u!=null)
			return new ResponseEntity<>(new Response<>(u), HttpStatus.OK);
		else
			return new ResponseEntity<>(new Response<>(u, "No se pudo registrar"), HttpStatus.BAD_REQUEST);
	}

	@PutMapping
	public ResponseEntity<Response<User>> updateUser(@RequestBody User user) {
		user.setPassword(encryption.encryptPassword(user.getPassword()));
		User u = service.update(user).orElse(null);

		if (u != null)
			return new ResponseEntity<>(new Response<>(u), HttpStatus.OK);
		else
			return new ResponseEntity<>(new Response<>(u, "No se pudo actualizar"), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable("id") int id) {
		User user = new User();
		user.setId(id);
		service.delete(user);
	}

}
