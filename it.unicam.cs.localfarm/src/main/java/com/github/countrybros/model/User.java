package com.github.countrybros.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to rapresent a user
 */
public class User {

    //TODO: See if there is a better way to manage users than this plain object

    private int id;
    private String name;
    //TODO: see if it is possible to remove the password from the object, keppeing it only on a future DB
    private String password;
    private String email;
    private List<String> roles = new ArrayList<>();

    public User(int id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
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

