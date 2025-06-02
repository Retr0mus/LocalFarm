package com.github.countrybros.model.acceptancesubmission;

import com.github.countrybros.model.product.ItemDetails;

/**
 * Represents a submission to recognize an existing product
 */
public class RecogniseProductAcceptanceSubmission extends AcceptanceSubmission {

    private ItemDetails product;
    private int qta;
    @Override
    public String getDetails() {
        return "";
    }
}
