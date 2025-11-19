package org.spring.authservice.exception;

public class LockedAccessException extends RuntimeException {
    public LockedAccessException(String message) {
        super(message);
    }
}
