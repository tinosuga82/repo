package com.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonalizadaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

	public PersonalizadaException() {
		super();
	}

	public PersonalizadaException(String message) {
		super(message);
	}

}
