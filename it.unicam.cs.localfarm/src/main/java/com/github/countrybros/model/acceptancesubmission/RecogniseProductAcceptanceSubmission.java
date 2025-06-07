package com.github.countrybros.model.acceptancesubmission;

import com.github.countrybros.model.product.ItemDetails;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

/**
 * Represents a submission to recognize an existing product
 */
@Entity
public class RecogniseProductAcceptanceSubmission extends AcceptanceSubmission {

    @Embedded
    private ItemDetails product;
    private int qta;

    @Override
    public String getDetails() {
        return "";
    }
}
