package com.github.countrybros.model;

import java.util.ArrayList;

/**
 * Factory for TransformedProductDetails.
 */
public class TransformedProductDetailsFactory implements ItemDetailsFactory {

    @Override
    public TransformedProductDetails create(String name, String description, Company company) {

        TransformedProductDetails product = new TransformedProductDetails(name, description, company);

        ArrayList<TransformationSteps> steps = new ArrayList<>();
        product.setSteps(steps);

        return product;

    }
}
