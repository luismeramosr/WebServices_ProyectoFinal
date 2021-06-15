package com.idat.webservices.api.controllers;


import java.io.IOException;
import java.util.Map;

import com.idat.webservices.api.ws.WebSocketHandler;
import com.idat.webservices.api.ws.WebSocketService;
import com.idat.webservices.domain.dto.LoginRequest;
import com.idat.webservices.domain.dto.LoginResponse;
import com.idat.webservices.domain.services.UserDetailsServiceOverride;
import com.idat.webservices.persistence.models.User;
import com.idat.webservices.persistence.services.UserService;
import com.idat.webservices.util.Console;
import com.idat.webservices.util.JWT.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	UserDetailsServiceOverride userDetailsSO;

	@Autowired
	JWTUtil jwtUtil;

	@Autowired
	UserService service;

	@PostMapping("login")
	@ResponseBody
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		try {
			// Autenticación con spring
			authManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			// Obtenemos los detalles de usuario, servira para generar el token y brindar permisos.
			UserDetails details = userDetailsSO.loadUserByUsername(request.getUsername());
			// Generamos el JWT
			String jwt = jwtUtil.generateToken(details);
			// Creamos el objeto user buscandolo por username
			User user = service.findById("O00001").get();
			// Enviamos la respuesta si todo esta correcto
			return new ResponseEntity<>(new LoginResponse(user, jwt), HttpStatus.OK);
		} catch (BadCredentialsException err) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

}
