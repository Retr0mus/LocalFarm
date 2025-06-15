package com.github.countrybros.model.acceptancesubmission;

import com.github.countrybros.model.product.ItemDetails;
import jakarta.persistence.*;

/**
 * Represents a submission to edit details of an existing product.
 */
@Entity
@DiscriminatorValue("editProduct")
public class EditProductAcceptanceSubmission extends AcceptanceSubmission {


    private int productToEditId;

    private int productChangeId;

    public EditProductAcceptanceSubmission() {}

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
