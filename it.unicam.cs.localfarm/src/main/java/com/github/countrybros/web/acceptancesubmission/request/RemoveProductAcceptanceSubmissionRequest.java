package com.github.countrybros.web.acceptancesubmission.request;


public class RemoveProductAcceptanceSubmissionRequest extends AcceptanceSubmissionRequest {
    private int productId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
