package com.github.countrybros.application.errors;

/**
 * This error is used to represents relevant coding mistakes, little oversights that
 * causes system malfunctions.
 */
public class SevereCodingErrorException extends RuntimeException {

    public SevereCodingErrorException(String message) {
        super(message);
    }
}
