package com.devsu.reports.infrastructure.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class HTTPExceptionHandler {

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
        log.error("Error: {} - {}", ex.getClass().getName(), ex.getMessage(), ex);
        error.put("message", "Ocurrió un error interno en el servidor: " + ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
