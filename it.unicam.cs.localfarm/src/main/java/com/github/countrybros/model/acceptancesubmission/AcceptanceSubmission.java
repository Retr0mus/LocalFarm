package com.github.countrybros.model.acceptancesubmission;

import com.github.countrybros.model.user.Company;
import com.github.countrybros.model.user.User;
import jakarta.persistence.*;

/**
 * Represents an abstract product acceptance submission
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "submission_type")
public abstract class AcceptanceSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Embedded
    private Company sender;
    @Embedded
    private User curator;
    private boolean accepted;

    public User getCurator() {
        return curator;
    }

    public Company getSender() {
        return sender;
    }

    //TODO: Change this shit immediately!
    public abstract String getDetails();


    public void accept() {
        this.accepted = true;
    }

}
