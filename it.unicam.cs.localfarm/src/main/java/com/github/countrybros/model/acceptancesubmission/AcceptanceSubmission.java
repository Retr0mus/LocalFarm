package com.github.countrybros.model.acceptancesubmission;

import com.github.countrybros.model.user.Company;
import com.github.countrybros.model.user.User;

/**
 * Represents an abstract product acceptance submission
 */
public abstract class AcceptanceSubmission {
    private Company sender;
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
