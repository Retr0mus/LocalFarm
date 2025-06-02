package com.github.countrybros.model.acceptancesubmission;

import com.github.countrybros.model.product.ItemDetails;

/**
 * Represents a submission to add a new product for acceptance.
 */
public class AddProductAcceptanceSubmission extends AcceptanceSubmission {

    private ItemDetails itemDetails;
    @Override
    public String getDetails() {
        return "";
    }
}
