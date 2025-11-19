package org.spring.restaurantsservice.exception;

public class LockedAccessException extends RuntimeException {
    public LockedAccessException(String message) {
        super(message);
    }
}
