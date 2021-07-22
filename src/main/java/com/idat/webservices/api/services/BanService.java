package com.idat.webservices.api.services;

import java.util.HashMap;
import java.util.Map;

import com.idat.webservices.persistence.models.User;
import com.idat.webservices.persistence.services.UserService;
import com.idat.webservices.util.Console;
import com.idat.webservices.util.Helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BanService {

	private Map<String, Integer> requestCount = new HashMap<>();

	@Autowired
	private UserService service;

	@Autowired
	private Helpers helpers;

	public void manageBans(String username) {
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
		User user = service.findByUsername(username).orElse(null);
		if (user.getRole().getName().equals("Operario")) {
			user.setActive(false);
			service.update(user);
		} else if (user.getRole().getName().equals("Administrador")) {
			// Desactivamos al usuario y lo reactivamos en 10 segundos
			// este tiempo luego sera de 180 segundos o 3 minutos
			user.setActive(false);
			service.update(user);
			helpers.setTimeout(() -> {
				user.setActive(true);
				service.update(user);
			}, 10);
		}
		Console.log(String.format("User \"%s\", has been disabled.", user.getUsername()));
	}

	public void unBanUser(User user) {
		user.setActive(true);
		service.update(user).orElse(null);
		requestCount.put(user.getUsername(), 0);
	}

}
