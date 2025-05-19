package com.github.countrybros.model;

import java.util.ArrayList;

/**
 * Details for a simple product.
 */
public class SimpleProductDetails extends ItemDetails {

    /**
     * List of the certifications that the product contains
     */
    private ArrayList<Certification> certifications;

    public SimpleProductDetails(String name, String description, Company producer) {

        super(name, description, producer);
    }

    public ArrayList<Certification> getCertifications() {

        return certifications;
    }

    public void setCertifications(ArrayList<Certification> certifications) {

        this.certifications = certifications;
    }
}
