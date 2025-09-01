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
    private int id;

    @OneToMany
    private List<SimpleProduct> ingredients;

    private Location location;

    private String description;

    int getId() {
        return id;
    }

    List<SimpleProduct> getIngredients() {
        return ingredients;
    }

    Location getLocation() {
        return location;
    }

    String getDescription() {
        return description;
    }

    public void setIngredients(List<SimpleProduct> ingredients) {
        this.ingredients = ingredients;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
