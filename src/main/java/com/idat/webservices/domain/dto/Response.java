package com.idat.webservices.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
	
	private T data;
	private Long timestamp;

	public Response(T _data) {
		data = _data;
		timestamp = System.currentTimeMillis() / 1000;
	}
}
