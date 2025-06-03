package com.github.countrybros.model.product;

import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Details for a simple product.
 */
@Entity
public class SimpleProductDetails extends ItemDetails {

    private List<Certification> certifications;

    public List<Certification> getCertifications() { return certifications; }

    public void setCertifications(ArrayList<Certification> certifications) {

        this.certifications = certifications;
    }
}
