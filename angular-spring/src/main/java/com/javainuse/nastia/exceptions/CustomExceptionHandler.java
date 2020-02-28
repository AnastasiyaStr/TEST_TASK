package com.javainuse.nastia.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler
        extends ResponseEntityExceptionHandler {
    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<?> handleEmptyResultDataAccessException(Exception e, WebRequest req) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
