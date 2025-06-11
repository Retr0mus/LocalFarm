package com.github.countrybros.model.product;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Certification details.
 */
@Embeddable
public class Certification {

    private String name;
    private String description;

    public Certification() {}

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
