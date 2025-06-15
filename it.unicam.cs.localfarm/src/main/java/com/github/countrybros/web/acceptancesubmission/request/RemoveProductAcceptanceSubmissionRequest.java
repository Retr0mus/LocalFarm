package com.github.countrybros.web.acceptancesubmission.request;

import com.github.countrybros.model.acceptancesubmission.AcceptanceSubmission;


public class RemoveProductAcceptanceSubmissionRequest extends AcceptanceSubmissionRequest {
    private int productId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
