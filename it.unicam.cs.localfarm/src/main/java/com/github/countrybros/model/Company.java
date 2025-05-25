package com.github.countrybros.model;

/**
 * Class to represent a company
 */
public class Company {
    private int id;
    private String name;
    private String email;
    private String password;
    private String description;
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

