package com.github.countrybros.model.submission;

import jakarta.persistence.*;

/**
 * Represents a submission to recognize an existing item
 */
@Entity
@DiscriminatorValue("recogniseProduct")
public class RecogniseProductSubmission extends Submission {

    private int stockId;
    private int qta;

    public RecogniseProductSubmission(int sellerId, int stockId, int qta) {
        super(sellerId);
        this.stockId = stockId;
        this.qta = qta;
    }

    public RecogniseProductSubmission() {}


    public int getStockId() {
        return stockId;
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    public void setStockId(int productId) {
        this.stockId = productId;
    }
}
