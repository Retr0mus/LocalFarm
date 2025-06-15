package com.github.countrybros.web.acceptancesubmission.request;

import com.github.countrybros.model.acceptancesubmission.AcceptanceSubmission;


public class EditProductAcceptanceSubmissionRequest extends AcceptanceSubmissionRequest {

    private int productToEditId;
    private int productChangeId;

    public int getProductToEditId() {
        return productToEditId;
    }

    public void setProductToEditId(int productToEditId) {
        this.productToEditId = productToEditId;
    }

    public int getProductChangeId() {
        return productChangeId;
    }

    public void setProductChangeId(int productChangeId) {
        this.productChangeId = productChangeId;
    }
}
