package com.github.countrybros.model;

import java.util.ArrayList;

/**
 * Factory for SimpleProductDetails.
 */
public class SimpleProductDetailsFactory implements ItemDetailsFactory {

    @Override
    public SimpleProductDetails create(String name, String description, Company company) {

        SimpleProductDetails product = new SimpleProductDetails(name, description, company);

        ArrayList<Certification> certifications = new ArrayList<>();
        product.setCertifications(certifications);

        return product;
    }
}
