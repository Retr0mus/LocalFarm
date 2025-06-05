package com.github.countrybros.application.errors;

public class FoundInRepositoryException extends RuntimeException {
    public FoundInRepositoryException(String message) {
        super(message);
    }
}
