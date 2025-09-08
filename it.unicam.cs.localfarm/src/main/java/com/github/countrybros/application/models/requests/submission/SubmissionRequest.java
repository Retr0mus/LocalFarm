package com.github.countrybros.application.models.requests.submission;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "typeForSpringBoot"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddProductSubmissionRequest.class, name = "addProduct"),
        @JsonSubTypes.Type(value = RecogniseProductSubmissionRequest.class, name = "recogniseProduct")
})
public abstract class SubmissionRequest {

    private String type;

    @NotNull
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