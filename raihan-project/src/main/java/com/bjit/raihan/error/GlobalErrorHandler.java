package com.bjit.raihan.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        return buildResponseEntity("entity not found", HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> buildResponseEntity(String message, HttpStatus status) {
        return new ResponseEntity<>(message, status);
    }
}
