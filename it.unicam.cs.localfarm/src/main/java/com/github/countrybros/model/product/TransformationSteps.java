package com.github.countrybros.model.product;

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
    private ArrayList<ItemDetails> details;

    /**
     * TODO location of the step.
     */

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<ItemDetails> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<ItemDetails> details) {
        this.details = details;
    }

}
