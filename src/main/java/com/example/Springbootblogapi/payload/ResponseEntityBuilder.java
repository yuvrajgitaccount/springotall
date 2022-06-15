package com.example.Springbootblogapi.payload;

import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {
	public static ResponseEntity<Object>errEntity(ApiErrors errors)
	{
		return new ResponseEntity<>(errors,errors.getStatus());
	}
}
