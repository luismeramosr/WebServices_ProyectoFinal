package com.idat.webservices.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Encryption {

	private BCryptPasswordEncoder encoder;

	private Encryption() {
		encoder = new BCryptPasswordEncoder();
	}

	public String encryptPassword(String password) {
		return encoder.encode(password);
	}
}
