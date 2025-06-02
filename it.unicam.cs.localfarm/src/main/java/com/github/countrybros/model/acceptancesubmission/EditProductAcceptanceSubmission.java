package com.github.countrybros.model.acceptancesubmission;

import com.github.countrybros.model.product.ItemDetails;

/**
 * Represents a submission to edit details of an existing product.
 */
public class EditProductAcceptanceSubmission extends AcceptanceSubmission {

    private ItemDetails productToEdit;
    private ItemDetails productToChange;
    @Override
    public String getDetails() {
        return "";
    }
}
