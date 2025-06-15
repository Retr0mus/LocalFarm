package com.github.countrybros.model.product;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Details for a simple product.
 */
@Entity
@DiscriminatorValue("simpleProductDetails")
public class SimpleProductDetails extends ItemDetails {

    //TODO: ManyToMany
    @ElementCollection
    private List<Certification> certifications = new ArrayList<>();

    public List<Certification> getCertifications() { return certifications; }

    public void setCertifications(ArrayList<Certification> certifications) {

        this.certifications = certifications;
    }
}
