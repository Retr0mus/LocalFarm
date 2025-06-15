package com.github.countrybros.model.acceptancesubmission;

import com.github.countrybros.model.product.ItemDetails;
import jakarta.persistence.*;

/**
 * Represents a submission to recognize an existing product
 */
@Entity
@DiscriminatorValue("recogniseProduct")
public class RecogniseProductAcceptanceSubmission extends AcceptanceSubmission {

    //TODO
    private int productId;
    private int qta;

    public RecogniseProductAcceptanceSubmission() {}


    public int getProductId() {
        return productId;
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
