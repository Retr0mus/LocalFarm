package com.github.countrybros.model;

/**
 * Certification details.
 */
public class Certification {
    private String name;
    private String description;


    public Certification(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
