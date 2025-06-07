package com.github.countrybros.model.acceptancesubmission;

import com.github.countrybros.model.product.ItemDetails;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

/**
 * Represents a submission to add a new product for acceptance.
 */
@Entity
public class AddProductAcceptanceSubmission extends AcceptanceSubmission {

    @Embedded
    private ItemDetails itemDetails;
    @Override
    public String getDetails() {
        return "";
    }
}
