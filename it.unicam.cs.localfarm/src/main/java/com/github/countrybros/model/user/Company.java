package com.github.countrybros.model.user;

import com.github.countrybros.application.user.IPaymentMethod;
import com.github.countrybros.model.event.Invitation;

import java.util.ArrayList;

/**
 * Class to represent a company
 */
public class Company {
    private int id;
    private String name;
    private String email;
    private String password;
    private String description;
    private ArrayList<Invitation> invitations;
    private IPaymentMethod method;

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

