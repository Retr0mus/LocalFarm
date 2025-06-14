package com.github.countrybros.application.errors;

public class RequestAlreadySatisfiedException extends RuntimeException {
    public RequestAlreadySatisfiedException(String message) {
        super(message);
    }
}
