package com.github.countrybros.model.submission;

import jakarta.persistence.*;

/**
 * Represents a submission to add a new item for acceptance.
 */
@Entity
@DiscriminatorValue("addProduct")
public class AddProductSubmission extends Submission {

    private int itemId;

    public AddProductSubmission() {

    }

    public AddProductSubmission(int senderId, int itemId) {
        super(senderId);
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
