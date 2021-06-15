package com.idat.webservices.domain.dto;

import com.idat.webservices.persistence.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	private User user;
	private String jwt;
}
