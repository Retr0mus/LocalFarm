package com.github.countrybros.application.errors;

/**
 * Exception that describes the failure in the core of the operation,
 * after finding out that it's not possible to successfully complete it.
 */
public class ImpossibleRequestException extends RuntimeException {
    public ImpossibleRequestException(String message) {
        super(message);
    }
}
