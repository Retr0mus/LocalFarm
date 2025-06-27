package com.github.countrybros.application.errors;

/**
 * Exception to cover code known flaws
 */
public class FunctionNotAvailableException extends RuntimeException {
    public FunctionNotAvailableException(String message) {
        super(message);
    }
}
