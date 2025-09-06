package com.github.countrybros.application.models.requests.submission;


import jakarta.validation.constraints.NotNull;

public class AddProductSubmissionRequest extends SubmissionRequest {

    @NotNull
    private int itemId;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }


}
