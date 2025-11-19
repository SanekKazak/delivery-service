package org.spring.restaurantsservice.controller;

import jakarta.validation.ValidationException;
import org.spring.restaurantsservice.exception.FileExtraSizeException;
import org.spring.restaurantsservice.exception.LockedAccessException;
import org.spring.restaurantsservice.exception.NotFoundEntityByIdException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcController {
    @ExceptionHandler(NotFoundEntityByIdException.class)
    public ResponseEntity<String> notFound(NotFoundEntityByIdException ex) {
        return ResponseEntity
                .status(400)
                .body(ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> validation(ValidationException ex) {
        return ResponseEntity
                .status(400)
                .body(ex.getMessage());
    }

    @ExceptionHandler(FileExtraSizeException.class)
    public ResponseEntity<String> validation(FileExtraSizeException ex) {
        return ResponseEntity
                .status(400)
                .body(ex.getMessage());
    }

    @ExceptionHandler(LockedAccessException.class)
    public ResponseEntity<String> validation(LockedAccessException ex) {
        return ResponseEntity
                .status(403)
                .body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> general(RuntimeException ex) {
        return ResponseEntity
                .status(500)
                .body(ex.getMessage());
    }
}
