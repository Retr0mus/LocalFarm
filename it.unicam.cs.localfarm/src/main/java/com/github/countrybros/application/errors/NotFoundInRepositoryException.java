package com.github.countrybros.application.errors;

public class NotFoundInRepositoryException extends RuntimeException {
    public NotFoundInRepositoryException(String message) {
        super(message);
    }
}
