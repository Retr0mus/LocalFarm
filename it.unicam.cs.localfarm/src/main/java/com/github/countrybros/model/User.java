package com.github.countrybros.model;

/**
 * Class that represents a user of the website.
 */
public class User {

    //TODO: See if there is a better way to manage users than this plain object

    private int id;
    private String name;
    private String email;
    //TODO: see if it is possible to remove the password from the object, keppeing it only on a future DB

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
