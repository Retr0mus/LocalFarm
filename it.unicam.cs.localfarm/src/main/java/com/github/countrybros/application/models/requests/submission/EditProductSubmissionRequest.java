package com.github.countrybros.application.models.requests.submission;

@Deprecated
public class EditProductSubmissionRequest extends SubmissionRequest {

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
