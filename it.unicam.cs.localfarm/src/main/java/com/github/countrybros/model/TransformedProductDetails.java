package com.github.countrybros.model;


import java.util.List;

/**
 * Details for a transformed product, composed of simple products.
 */
public class TransformedProductDetails extends SimpleProductDetails {
    private List<TrasformationStep> steps;


    public List<TrasformationStep> getSteps() {
        return steps;
    }


}
