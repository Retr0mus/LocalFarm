package com.github.countrybros.model.acceptancesubmission;

import com.github.countrybros.model.product.ItemDetails;
import jakarta.persistence.*;

/**
 * Represents a submission to add a new product for acceptance.
 */
@Entity
@DiscriminatorValue("addProduct")
public class AddProductAcceptanceSubmission extends AcceptanceSubmission {

    private int itemDetailsId;

    public AddProductAcceptanceSubmission() {}

    public int getItemDetailsId() {
        return itemDetailsId;
    }

    public void setItemDetailsId(int itemDetailsId) {
        itemDetailsId = itemDetailsId;
    }
}
