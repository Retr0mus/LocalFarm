package com.github.countrybros.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Details for a simple product.
 */
public class SimpleProductDetails extends ItemDetails {

    private List<Certification> certifications = new ArrayList<>();

    public List<Certification> getCertifications() { return certifications; }

    public void setCertifications(ArrayList<Certification> certifications) {

        this.certifications = certifications;
    }
}
