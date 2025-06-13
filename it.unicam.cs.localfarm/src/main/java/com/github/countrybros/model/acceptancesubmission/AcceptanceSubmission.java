package com.github.countrybros.model.acceptancesubmission;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.model.user.User;
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
        @JsonSubTypes.Type(value = AddProductAcceptanceSubmission.class, name = "addProduct"),
        @JsonSubTypes.Type(value = EditProductAcceptanceSubmission.class, name = "editProduct"),
        @JsonSubTypes.Type(value = RecogniseProductAcceptanceSubmission.class, name = "recogniseProduct"),
        @JsonSubTypes.Type(value = RemoveProductAcceptanceSubmission.class, name = "removeProduct")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "submission_type")
public abstract class AcceptanceSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Transient
    private Company sender;
    @Transient
    private User curator;
    private boolean accepted;

    public AcceptanceSubmission() {}

    public User getCurator() {
        return curator;
    }

    public Company getSender() {
        return sender;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
    //TODO: Change this shit immediately!
    //public abstract String getDetails();

    //TODO aggiungi la logica del controllo con il curatore
    public void accept() {
        this.accepted = true;
    }

    public boolean isAccepted() {
        return accepted;
    }
}
