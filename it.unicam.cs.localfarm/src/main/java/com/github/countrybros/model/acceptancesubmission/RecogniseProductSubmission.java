package com.github.countrybros.model.acceptancesubmission;

import jakarta.persistence.*;

/**
 * Represents a submission to recognize an existing product
 */
@Entity
@DiscriminatorValue("recogniseProduct")
public class RecogniseProductSubmission extends Submission {

    private int itemId;
    private int qta;

    public RecogniseProductSubmission() {
        super();
    }

    public RecogniseProductSubmission(int sellerId, int itemId, int qta) {
        super(sellerId);
        this.itemId = itemId;
        this.qta = qta;
    }


    public int getItemId() {
        return itemId;
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    public void setItemId(int productId) {
        this.itemId = productId;
    }
}
