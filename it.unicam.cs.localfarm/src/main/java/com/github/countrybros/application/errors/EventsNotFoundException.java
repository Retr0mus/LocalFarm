package com.github.countrybros.application.errors;

public class EventsNotFoundException extends RuntimeException {
    public EventsNotFoundException(String message) {
        super(message);
    }
}
