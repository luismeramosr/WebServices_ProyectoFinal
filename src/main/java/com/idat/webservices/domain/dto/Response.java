package com.idat.webservices.domain.dto;

import java.lang.StackWalker.Option;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
	
	private T data;
	private Long timestamp;
	private String errorMessage;

	public Response(T _data) {
		data = _data;
		timestamp = System.currentTimeMillis() / 1000;
	}

	public Response(T _data, String msg) {
		data = _data;
		timestamp = System.currentTimeMillis() / 1000;
		errorMessage = msg;
	}
}
