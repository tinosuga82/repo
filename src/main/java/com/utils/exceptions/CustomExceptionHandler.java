package com.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
 
@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler 
{
	@ExceptionHandler(PersonalizadaException.class)
    public final ResponseEntity<Object> PersonalizadaException(Exception ex, WebRequest request) {
        PersonalizadaException error = new PersonalizadaException(ex.getMessage());
        logger.warn(ex.getMessage());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
}
