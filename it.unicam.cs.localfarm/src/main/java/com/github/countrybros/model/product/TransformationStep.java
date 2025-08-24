package com.github.countrybros.model.product;

import com.github.countrybros.application.Location;
import jakarta.persistence.*;

import java.util.List;

/**
 * Represents a step in a product transformation process.
 */
@Entity
public class TransformationStep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @OneToMany
    private List<SimpleProduct> ingredients;

    private Location location;

    private String description;
}
