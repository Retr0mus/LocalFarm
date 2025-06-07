package com.github.countrybros.model.user;

import jakarta.persistence.*;

import java.util.List;

/**
 * Class to rapresent a user
 */
@Embeddable
public class User {

    //TODO: See if there is a better way to manage users than this plain object
    //TODO: see if it is possible to remove the password from the object, keppeing it only on a future DB

    private String name;
    private String password;
    private String email;
    @ElementCollection
    private List<String> roles;
    @Embedded
    private Cart cart;

    public User() {

    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }
}

