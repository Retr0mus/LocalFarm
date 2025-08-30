package com.github.countrybros.model.acceptancesubmission;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

/**
 * Represents an abstract product acceptance submission
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,            // Specifies that the type information will be included as a logical name (simple string) identifying the concrete class.
        include = JsonTypeInfo.As.PROPERTY,   // Specifies that this type information will be included as a property inside the JSON object.
        property = "type"                      // Defines the name of the JSON property that will hold the type information (here, "type").
)
@JsonSubTypes({
        // Defines the possible subtypes and associates each subtype with a specific name value in the "type" property.
        @JsonSubTypes.Type(value = AddProductSubmission.class, name = "addProduct"),
        @JsonSubTypes.Type(value = EditProductSubmission.class, name = "editProduct"),
        @JsonSubTypes.Type(value = RecogniseProductSubmission.class, name = "recogniseProduct"),
        @JsonSubTypes.Type(value = RemoveProductSubmission.class, name = "removeProduct")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "submission_type")
public abstract class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int senderId;
    private int curatorId;
    private SubmissionStatus status;

    public Submission() {}

    public void setStatus(SubmissionStatus status) {
        this.status = status;
    }

    public void assignCurator(int curatorId) {
        this.curatorId = curatorId;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getCuratorId() {
        return curatorId;
    }

    public int getId() {
        return id;
    }
}
