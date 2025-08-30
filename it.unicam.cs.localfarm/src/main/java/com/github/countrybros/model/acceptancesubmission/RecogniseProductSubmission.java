package com.github.countrybros.model.acceptancesubmission;

import jakarta.persistence.*;

/**
 * Represents a submission to recognize an existing product
 */
@Entity
@DiscriminatorValue("recogniseProduct")
public class RecogniseProductSubmission extends Submission {

    //TODO
    private int productId;
    private int qta;

    public RecogniseProductSubmission() {}


    public int getProductId() {
        return productId;
    }

    public int getQty() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
