package com.github.countrybros.web.acceptancesubmission.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "typeForSpringBoot"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddProductAcceptanceSubmissionRequest.class, name = "addProduct"),
        @JsonSubTypes.Type(value = EditProductAcceptanceSubmissionRequest.class, name = "editProduct"),
        @JsonSubTypes.Type(value = RecogniseProductAcceptanceSubmissionRequest.class, name = "recogniseProduct"),
        @JsonSubTypes.Type(value = RemoveProductAcceptanceSubmissionRequest.class, name = "removeProduct")
})
public abstract class AcceptanceSubmissionRequest {

    private String type;
    private int senderId;
    private String typeForSpringBoot;


    public String getType() {
        return type;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
}