package com.github.countrybros.model.acceptancesubmission;

import jakarta.persistence.*;

/**
 * Represents a submission to remove an existing product
 */
@Entity
@DiscriminatorValue("removeProduct")
@Deprecated
public class RemoveProductSubmission extends Submission {


    private int productId;

    public RemoveProductSubmission() {
        super(0);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
