package com.github.countrybros.model.acceptancesubmission;

import com.github.countrybros.model.product.ItemDetails;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

/**
 * Represents a submission to edit details of an existing product.
 */
@Entity
public class EditProductAcceptanceSubmission extends AcceptanceSubmission {

    @Embedded
    private ItemDetails productToEdit;

    @Override
    public String getDetails() {
        return "";
    }
}
