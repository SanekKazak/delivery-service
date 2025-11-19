package org.spring.authservice.exception;

import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.InvocationTargetException;

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

    @ExceptionHandler(ProvidedPasswordNotEqualsWithSaved.class)
    public ResponseEntity<String> validation(ProvidedPasswordNotEqualsWithSaved ex) {
        return ResponseEntity
                .status(406)
                .body(ex.getMessage());
    }

    @ExceptionHandler(FileExtraSizeException.class)
    public ResponseEntity<String> validation(FileExtraSizeException ex) {
        return ResponseEntity
                .status(400)
                .body(ex.getMessage());
    }
    @ExceptionHandler(InvocationTargetException.class)
    public ResponseEntity<String> validation(InvocationTargetException ex) {
        return ResponseEntity
                .status(400)
                .body("this user already exists:" + ex.getMessage());
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
