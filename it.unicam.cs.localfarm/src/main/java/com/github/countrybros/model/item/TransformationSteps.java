package com.github.countrybros.model.item;

import java.util.ArrayList;

/**
 * Represents a single step of a @TransformedProductDetails production,
 * contains a description of the step, the ingredients used, and the location.
 */
@Deprecated
public class TransformationSteps {

    /**
     * Description of the step.
     */
    private String description;

    /**
     * The ingredients used.
     */
    private ArrayList<Item> details;

    /**
     * TODO location of the step.
     */

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Item> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<Item> details) {
        this.details = details;
    }

}
