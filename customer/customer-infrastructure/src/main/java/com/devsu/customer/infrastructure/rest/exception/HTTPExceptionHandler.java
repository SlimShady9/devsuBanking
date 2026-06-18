package com.devsu.customer.infrastructure.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;

@RestControllerAdvice
@Slf4j
public class HTTPExceptionHandler {

    // Business Logic Exceptions
    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Map<String, String>> handleIllegalArgumentExceptions(IllegalArgumentException ex) {
        log.error("Error: {}", ex.getMessage(), ex);
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Business Logic Exceptions
    @ExceptionHandler({ MissingResourceException.class })
    public ResponseEntity<Map<String, String>> handleMissingResourceExceptions(MissingResourceException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Infrastructure Exceptions

    @ExceptionHandler({ NoResourceFoundException.class })
    public ResponseEntity<Map<String, String>> handleNoResourceFoundException(NoResourceFoundException ex) {
        log.error("Error: {}", ex.getMessage(), ex);
        Map<String, String> error = new HashMap<>();
        error.put("message", "Recurso no encontrado");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ HttpMessageConversionException.class })
    public ResponseEntity<Map<String, String>> handleHttpMessageConversionExceptions(
            HttpMessageConversionException ex) {
        log.error("Error: {}", ex.getMessage(), ex);
        Map<String, String> error = new HashMap<>();
        error.put("message", "Parametros incorrectos");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllOtherExceptions(Exception ex) {
        Map<String, String> error = new HashMap<>();
        log.error("Error: {} - {}", ex.getClass().getName(), ex.getMessage());
        error.put("message", "Ocurrió un error interno en el servidor: " + ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
