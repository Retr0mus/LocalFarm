package com.github.countrybros.model.acceptancesubmission;

import jakarta.persistence.*;

/**
 * Represents a submission to edit details of an existing product.
 */
@Entity
@DiscriminatorValue("editProduct")
@Deprecated
public class EditProductSubmission extends Submission {


    private int productToEditId;

    private int productChangeId;

    public EditProductSubmission() {
        super(0);
    }

    public int getProductToEditId() {
        return productToEditId;
    }

    public int getProductChangeId() {
        return productChangeId;
    }

    public void setProductToEditId(int productToEditId) {
        this.productToEditId = productToEditId;
    }

    public void setProductChangeId(int productChangeId) {
        this.productChangeId = productChangeId;
    }
}
