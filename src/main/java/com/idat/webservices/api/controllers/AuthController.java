package com.idat.webservices.api.controllers;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.idat.webservices.api.ws.WebSocketHandler;
import com.idat.webservices.api.ws.WebSocketService;
import com.idat.webservices.domain.dto.LoginRequest;
import com.idat.webservices.domain.dto.LoginResponse;
import com.idat.webservices.domain.dto.Response;
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

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;

	private Map<String, Integer> requestCount = new HashMap<>();

	@PostMapping("login")
	@ResponseBody
	public ResponseEntity<Response<LoginResponse>> login(@RequestBody LoginRequest request) {
		try {
			// Creamos el objeto user buscandolo por username
			User user = userService.findByUsername(request.getUsername()).map(User -> {
				return User;
			}).orElse(null);

			if (user.isActive()) {
				LoginResponse response = authService.authenticate(user, request.getPassword());			
				requestCount.put(user.getUsername(), 1);
				return new ResponseEntity<>(new Response<LoginResponse>(response), HttpStatus.OK);
			} else {
				throw new BadCredentialsException("User is disabled");
			}
		} catch (BadCredentialsException err) {
			manageBans(request.getUsername());
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
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
		User user = userService.findByUsername(username).map(User -> {
			return User;
		}).orElse(null);

		if (user.getRole().getName().equals("Operario")) {
			user.setActive(false);
			userService.update(user);
		} else if (user.getRole().getName().equals("Administrador")) {
			// Desactivamos al usuario y lo reactivamos en 10 segundos
			// este tiempo luego sera de 180 segundos o 3 minutos
			user.setActive(false);
			userService.update(user);
			Helpers.setTimeout(() -> {
				user.setActive(true);
				userService.update(user);
			}, 10);
		}
		Console.log("User has been disabled.");
	}

}
