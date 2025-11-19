package org.spring.authservice.exception;

public class NotFoundEntityByIdException extends RuntimeException {
    public NotFoundEntityByIdException(String message) {
        super(message);
    }
}
