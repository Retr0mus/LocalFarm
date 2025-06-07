package com.github.countrybros.model.acceptancesubmission;

import com.github.countrybros.model.product.ItemDetails;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

/**
 * Represents a submission to remove an existing product
 */
@Entity
public class RemoveProductAcceptanceSubmission extends AcceptanceSubmission {

    @Embedded
    private ItemDetails product;

    @Override
    public String getDetails() {
        return "";
    }
}
