package org.spring.authservice.exception;

public class ProvidedPasswordNotEqualsWithSaved extends RuntimeException {
    public ProvidedPasswordNotEqualsWithSaved(String message) {
        super(message);
    }
}
