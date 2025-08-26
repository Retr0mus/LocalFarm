package com.github.countrybros.model.acceptancesubmission;

import jakarta.persistence.*;

/**
 * Represents a submission to add a new product for acceptance.
 */
@Entity
@DiscriminatorValue("addProduct")
public class AddProductSubmission extends Submission {

    private int itemDetailsId;

    public AddProductSubmission(int senderId) {
        super(senderId);
    }


    public int getItemDetailsId() {
        return itemDetailsId;
    }

    public void setItemDetailsId(int itemDetailsId) {
        this.itemDetailsId = itemDetailsId;
    }
}
