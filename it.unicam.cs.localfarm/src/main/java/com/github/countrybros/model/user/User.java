package com.github.countrybros.model.user;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

/**
 * Class to represent a user
 */
@Embeddable
public class User {

    //TODO: See if there is a better way to manage users than this plain object
    //TODO: see if it is possible to remove the password from the object, keppeing it only on a future DB

    private int id;
    private String name;
    private String password;
    private String email;
    private List<String> roles;
    private Cart cart;

    public User() {}

    public User(int id, String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
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

    public int getId() {
        return id;
    }
}

