package com.idat.webservices.api.controllers;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.idat.webservices.api.services.BanService;
import com.idat.webservices.domain.dto.LoginRequest;
import com.idat.webservices.domain.dto.LoginResponse;
import com.idat.webservices.domain.dto.Response;
import com.idat.webservices.domain.exceptions.AuthException;
import com.idat.webservices.domain.services.AuthService;
import com.idat.webservices.domain.services.UserDetailsServiceOverride;
import com.idat.webservices.persistence.models.User;
import com.idat.webservices.persistence.services.UserService;
import com.idat.webservices.util.Console;
import com.idat.webservices.util.Helpers;
import com.idat.webservices.util.JWT.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;

	@Autowired
	private BanService banService;

	@PostMapping("login")
	@ResponseBody
	public ResponseEntity<Response<LoginResponse>> login(@RequestBody LoginRequest request) {
		try {
			// Creamos el objeto user buscandolo por username
			User user = userService.findByUsername(request.getUsername()).orElse(null);

			if (user == null)
				throw new AuthException("Credenciales incorrectas!");

			if (!user.isInSchedule())
				throw new AuthException("Usuario fuera del horario");

			if (user.isActive()) {
				LoginResponse response = authService.authenticate(user, request.getPassword());
				return new ResponseEntity<>(new Response<>(response), HttpStatus.OK);
			} else {
				throw new AuthException("Usuario bloqueado");
			}
		} catch (BadCredentialsException err) {
			banService.manageBans(request.getUsername());
			return new ResponseEntity<>(new Response<>(null, "Credenciales incorrectas!"), HttpStatus.OK);
		} catch (AuthException err) {
			return new ResponseEntity<>(new Response<>(null, err.getMessage()), HttpStatus.OK);
		}
	}

}
