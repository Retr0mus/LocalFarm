package com.github.countrybros.application.models.requests.submission;


public class AddProductSubmissionRequest extends SubmissionRequest {
    private int itemId;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }


}
