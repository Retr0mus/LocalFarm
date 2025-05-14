package com.github.countrybros.model.product;

import java.util.ArrayList;
import java.util.List;

/**
 * Details for a simple product.
 */
public class SimpleProductDetails extends ItemDetails {
    private int id;
    private List<Certification> certifications = new ArrayList<>();


    public int getId() { return id; }
    public List<Certification> getCertifications() { return certifications; }

}
