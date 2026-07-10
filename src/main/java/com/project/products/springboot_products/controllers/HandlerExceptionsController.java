package com.project.products.springboot_products.controllers;

import java.time.LocalDateTime;
import com.project.products.springboot_products.exceptions.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.project.products.springboot_products.exceptions.NoEntityException;

@RestControllerAdvice
public class HandlerExceptionsController {
    @ExceptionHandler(NoEntityException.class)
    public ResponseEntity<Error> handleNoEntityException(NoEntityException ex) {
        Error error = new Error(ex.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Error error = new Error(ex.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        Error error = new Error(ex.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
