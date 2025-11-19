package org.spring.ordersservice.controller;

import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcController {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> validation(ValidationException ex) {
        return ResponseEntity
                .status(400)
                .body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> general(RuntimeException ex) {
        return ResponseEntity
                .status(500)
                .body(ex.getMessage());
    }
}
