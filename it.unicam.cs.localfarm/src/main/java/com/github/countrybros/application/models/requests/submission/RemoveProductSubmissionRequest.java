package com.github.countrybros.application.models.requests.submission;


public class RemoveProductSubmissionRequest extends SubmissionRequest {
    private int productId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
