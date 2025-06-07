package com.github.countrybros.model.acceptancesubmission;

import com.github.countrybros.model.product.ItemDetails;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

/**
 * Represents a submission to remove an existing product
 */
@Entity
@DiscriminatorValue("removeProduct")
public class RemoveProductAcceptanceSubmission extends AcceptanceSubmission {

    @Transient
    private ItemDetails product;

    public RemoveProductAcceptanceSubmission() {}
//    @Override
//    public String getDetails() {
//        return "";
//    }
}
