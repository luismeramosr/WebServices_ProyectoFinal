package com.idat.webservices.api.controllers;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private Helpers helpers;

	private Map<String, Integer> requestCount = new HashMap<>();

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
				requestCount.put(user.getUsername(), 0);
				return new ResponseEntity<>(new Response<>(response), HttpStatus.OK);
			} else {
				throw new AuthException("Usuario bloqueado");
			}
		} catch (BadCredentialsException err) {
			manageBans(request.getUsername());
			return new ResponseEntity<>(new Response<>(null, "Credenciales incorrectas!"), HttpStatus.OK);
		} catch (AuthException err) {
			return new ResponseEntity<>(new Response<>(null, err.getMessage()), HttpStatus.OK);
		}
	}

	private void manageBans(String username) {
		if (requestCount.get(username) != null) {
			int badRequests = requestCount.get(username);
			badRequests++;
			requestCount.put(username, badRequests);
			if (badRequests >= 3) {
				banUser(username);
			}
		} else {
			requestCount.put(username, 1);
		}
		Console.log(String.format("Bad requests: %s", requestCount.get(username)));
	}

	private void banUser(String username) {
		User user = userService.findByUsername(username).orElse(null);

		if (user.getRole().getName().equals("Operario")) {
			user.setActive(false);
			userService.update(user);
		} else if (user.getRole().getName().equals("Administrador")) {
			// Desactivamos al usuario y lo reactivamos en 10 segundos
			// este tiempo luego sera de 180 segundos o 3 minutos
			user.setActive(false);
			userService.update(user);
			helpers.setTimeout(() -> {
				user.setActive(true);
				userService.update(user);
			}, 10);
		}
		Console.log(String.format("User \"%s\", has been disabled.", user.getUsername()));
	}

}
