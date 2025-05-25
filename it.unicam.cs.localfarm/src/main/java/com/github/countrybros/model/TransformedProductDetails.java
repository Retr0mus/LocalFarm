package com.github.countrybros.model;


import java.util.ArrayList;

/**
 * Details for a transformed product, composed of simple products.
 */
public class TransformedProductDetails extends SimpleProductDetails {

    /**
     * Steps that defines the transformation process.
     */
    private ArrayList<TransformationStep> steps;

    public ArrayList<TransformationStep> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<TransformationStep> steps) {
        this.steps = steps;
    }
}
