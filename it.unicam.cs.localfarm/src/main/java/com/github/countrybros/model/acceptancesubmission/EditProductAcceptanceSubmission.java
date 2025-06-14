package com.github.countrybros.model.acceptancesubmission;

import com.github.countrybros.model.product.ItemDetails;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

/**
 * Represents a submission to edit details of an existing product.
 */
@Entity
@DiscriminatorValue("editProduct")
public class EditProductAcceptanceSubmission extends AcceptanceSubmission {

    @Transient
    private ItemDetails productToEdit;

    public EditProductAcceptanceSubmission() {}

//    @Override
//    public String getDetails() {
//        return "";
//    }
}
