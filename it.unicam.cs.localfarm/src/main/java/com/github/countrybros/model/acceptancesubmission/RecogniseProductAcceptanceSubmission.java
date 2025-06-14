package com.github.countrybros.model.acceptancesubmission;

import com.github.countrybros.model.product.ItemDetails;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

/**
 * Represents a submission to recognize an existing product
 */
@Entity
@DiscriminatorValue("recogniseProduct")
public class RecogniseProductAcceptanceSubmission extends AcceptanceSubmission {

    @Transient
    private ItemDetails product;
    private int qta;

    public RecogniseProductAcceptanceSubmission() {}

//    @Override
//    public String getDetails() {
//        return "";
//    }
}
