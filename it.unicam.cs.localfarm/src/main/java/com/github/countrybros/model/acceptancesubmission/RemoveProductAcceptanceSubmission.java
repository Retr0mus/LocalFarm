package com.github.countrybros.model.acceptancesubmission;

import com.github.countrybros.model.product.ItemDetails;

/**
 * Represents a submission to remove an existing product
 */
public class RemoveProductAcceptanceSubmission extends AcceptanceSubmission {

    private ItemDetails product;
    @Override
    public String getDetails() {
        return "";
    }
}
