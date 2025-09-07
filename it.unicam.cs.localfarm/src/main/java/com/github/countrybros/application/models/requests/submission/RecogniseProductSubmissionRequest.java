package com.github.countrybros.application.models.requests.submission;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class RecogniseProductSubmissionRequest extends SubmissionRequest {

    @NotNull
    private int stockId;

    @Positive(message = "Quantity must be positive")
    private int qta;

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }
}
