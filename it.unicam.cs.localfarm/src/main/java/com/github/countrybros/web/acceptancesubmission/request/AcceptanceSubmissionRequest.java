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
    private int id;
    private String type;
    private int curatorId;
    private int senderId;
    private boolean accepted;
    private String typeForSpringBoot;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getCuratorId() {
        return curatorId;
    }

    public int getSenderId() {
        return senderId;
    }

    public boolean isAccepted() {
        return accepted;
    }




}