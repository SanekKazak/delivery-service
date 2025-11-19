package org.spring.usersservice.exception;

public class NotFoundEntityByIdException extends RuntimeException {
    public NotFoundEntityByIdException(String message) {
        super(message);
    }
}
