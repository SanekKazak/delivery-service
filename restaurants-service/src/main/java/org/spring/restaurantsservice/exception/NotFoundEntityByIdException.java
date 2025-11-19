package org.spring.restaurantsservice.exception;

public class NotFoundEntityByIdException extends RuntimeException {
    public NotFoundEntityByIdException(String message) {
        super(message);
    }
}
