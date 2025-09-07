package com.github.countrybros.model.submission;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.user.User;
import jakarta.persistence.*;

/**
 * Represents an abstract item acceptance submission
 */

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,            // Specifies that the type information will be included as a logical name (simple string) identifying the concrete class.
        include = JsonTypeInfo.As.PROPERTY,   // Specifies that this type information will be included as a property inside the JSON object.
        property = "type"                      // Defines the name of the JSON property that will hold the type information (here, "type").
)
@JsonSubTypes({
        // Defines the possible subtypes and associates each subtype with a specific name value in the "type" property.
        @JsonSubTypes.Type(value = AddProductSubmission.class, name = "addProduct"),
        //@JsonSubTypes.Type(value = EditProductSubmission.class, name = "editProduct"),
        @JsonSubTypes.Type(value = RecogniseProductSubmission.class, name = "recogniseProduct"),
        //@JsonSubTypes.Type(value = RemoveProductSubmission.class, name = "removeProduct")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "submission_type")
public abstract class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Company sender;
    @ManyToOne(fetch = FetchType.EAGER)
    private User curator;
    @Enumerated(EnumType.STRING)
    private SubmissionStatus status;

    public Submission(Company sender) {
        this.sender = sender;
        this.status = SubmissionStatus.pending;
    }

    public Submission() {
        this.status = SubmissionStatus.pending;
    }

    public void setStatus(SubmissionStatus status) {
        this.status = status;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }


    public Company getSender() {
        return sender;
    }

    public void setSender(Company sender) {
        this.sender = sender;
    }

    public User getCurator() {
        return curator;
    }

    public void setCurator(User curator) {
        this.curator = curator;
    }
}
