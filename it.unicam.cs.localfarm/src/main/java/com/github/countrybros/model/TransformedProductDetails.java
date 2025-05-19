package com.github.countrybros.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Details for a transformed product, composed of simple products.
 */
public class TransformedProductDetails extends SimpleProductDetails {

    /**
     * List of
     */
    private List<TransformationSteps> steps;

    public TransformedProductDetails(String name, String description, Company producer) {

        super(name, description, producer);

        this.steps = new ArrayList<>();
    }

    public List<TransformationSteps> getSteps() {

        return steps;
    }


    public void setSteps(List<TransformationSteps> steps) {

        this.steps = steps;
    }
}
