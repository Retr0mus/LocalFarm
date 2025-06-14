package com.github.countrybros.model.acceptancesubmission;

import com.github.countrybros.model.product.ItemDetails;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

/**
 * Represents a submission to add a new product for acceptance.
 */
@Entity
@DiscriminatorValue("addProduct")
public class AddProductAcceptanceSubmission extends AcceptanceSubmission {

    @Transient
    private ItemDetails itemDetails;

    public AddProductAcceptanceSubmission() {}

//    @Override
//    public String getDetails() {
//        return "";
//    }
}
