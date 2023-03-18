package com.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PersonalizadaExceptionDos extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

	public PersonalizadaExceptionDos() {
		super();
	}

	public PersonalizadaExceptionDos(String message) {
		super(message);
	}

}
