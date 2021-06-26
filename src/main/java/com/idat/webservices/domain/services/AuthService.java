package com.idat.webservices.domain.services;

import com.idat.webservices.domain.dto.LoginResponse;
import com.idat.webservices.persistence.models.User;
import com.idat.webservices.persistence.services.UserService;
import com.idat.webservices.util.JWT.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	UserDetailsServiceOverride userDetailsSO;

	@Autowired
	JWTUtil jwtUtil;

	public LoginResponse authenticate(User user, String password) throws BadCredentialsException {
		// Autenticación con spring
		authManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), password));
		// Obtenemos los detalles de usuario, servira para generar el token y brindar
		// permisos.
		UserDetails details = userDetailsSO.loadUserByUsername(user.getUsername());
		// Generamos el JWT
		String jwt = jwtUtil.generateToken(details);
		// Enviamos la respuesta si todo esta correcto
		return new LoginResponse(user, jwt);
	}
}
