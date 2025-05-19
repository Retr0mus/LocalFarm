package com.github.countrybros.model;

/**
 * Represents an abstract product acceptance submission
 */
public abstract class AcceptanceSubmission {
    private Company sender;
    private User curator;

    public abstract String getDetails();

}
