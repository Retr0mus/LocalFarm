package com.github.countrybros.model.acceptancesubmission;

import com.github.countrybros.model.product.ItemDetails;
import jakarta.persistence.*;

/**
 * Represents a submission to remove an existing product
 */
@Entity
@DiscriminatorValue("removeProduct")
public class RemoveProductAcceptanceSubmission extends AcceptanceSubmission {


    private int productId;

    public RemoveProductAcceptanceSubmission() {}

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
