package com.idat.webservices.api.controllers;


import java.io.IOException;
import java.util.Map;

import com.idat.webservices.api.ws.WebSocketHandler;
import com.idat.webservices.api.ws.WebSocketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	WebSocketService service;

	@GetMapping("login")
	public String login() {
		try {
			service.broadcastStock("10");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "it works";
	}

}
