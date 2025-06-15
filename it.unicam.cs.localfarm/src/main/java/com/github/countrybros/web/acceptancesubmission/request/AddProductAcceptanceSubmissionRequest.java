package com.github.countrybros.web.acceptancesubmission.request;

import com.github.countrybros.model.acceptancesubmission.AcceptanceSubmission;

public class AddProductAcceptanceSubmissionRequest extends AcceptanceSubmissionRequest {
    private int itemDetailsId;

    public int getItemDetailsId() {
        return itemDetailsId;
    }

    public void setItemDetailsId(int itemDetailsId) {
        this.itemDetailsId = itemDetailsId;
    }


}
