package com.github.countrybros.application.errors;

public class RoleAlreadyAssignedException extends RuntimeException {
    public RoleAlreadyAssignedException(String message) {
        super(message);
    }
}
