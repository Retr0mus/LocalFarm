package com.github.countrybros.model.product;

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
    private List<ItemDetails> ingredients;
   //TODO add location
    private String description;
}
