package com.github.countrybros.application.models.requests.submission;


public class RecogniseProductSubmissionRequest extends SubmissionRequest {
    private int productId;
    private int qta;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }
}
