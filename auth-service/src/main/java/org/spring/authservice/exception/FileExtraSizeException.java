package org.spring.authservice.exception;

public class FileExtraSizeException extends RuntimeException {
    public FileExtraSizeException(String message) {
        super(message);
    }
}
