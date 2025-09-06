package com.github.countrybros.model.submission;

import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.stock.Stock;
import jakarta.persistence.*;

/**
 * Represents a submission to recognize an existing item
 */
@Entity
@DiscriminatorValue("recogniseProduct")
public class RecogniseProductSubmission extends Submission {

    @ManyToOne
    private Stock stock;
    private int qta;

    public RecogniseProductSubmission(Company sender, Stock stock, int qta) {
        super(sender);
        this.stock = stock;
        this.qta = qta;
    }

    public RecogniseProductSubmission() {}



    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
