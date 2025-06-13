package com.github.countrybros.model.user;

import com.github.countrybros.model.event.Invitation;
import jakarta.persistence.Embeddable;

import java.util.ArrayList;

/**
 * Class to represent a company
 *
 * TODO: remove embeddable
 */
public class Company {
    private int id;
    private String name;
    private String email;
    private String password;
    private String description;
    private ArrayList<Invitation> invitations;
   //TODO add location

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
}

