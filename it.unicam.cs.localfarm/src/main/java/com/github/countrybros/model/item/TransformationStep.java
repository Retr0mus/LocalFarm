package com.github.countrybros.model.item;

import com.github.countrybros.model.utils.Location;
import jakarta.persistence.*;

import java.util.List;

/**
 * Represents a step in a item transformation process.
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

    public List<SimpleProduct> getIngredients() {
        return ingredients;
    }

    public Location getLocation() {
        return location;
    }

    public String getDescription() {
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
