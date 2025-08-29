package com.github.countrybros.web.acceptancesubmission.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "typeForSpringBoot"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddProductSubmissionRequest.class, name = "addProduct"),
        @JsonSubTypes.Type(value = EditProductSubmissionRequest.class, name = "editProduct"),
        @JsonSubTypes.Type(value = RecogniseProductSubmissionRequest.class, name = "recogniseProduct"),
        @JsonSubTypes.Type(value = RemoveProductSubmissionRequest.class, name = "removeProduct")
})
public abstract class SubmissionRequest {

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