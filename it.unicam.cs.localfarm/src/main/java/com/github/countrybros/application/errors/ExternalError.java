package com.github.countrybros.application.errors;

/**
 * Error that occurs when an external system has a problem on fulfilling his duty.
 */
public class ExternalError extends RuntimeException {
    public ExternalError(String message) {
        super(message);
    }
}
