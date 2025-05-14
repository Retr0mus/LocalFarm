package com.github.countrybros.model;

/**
 * Class to represent a company
 */
public class Company {

    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String password;
    //TODO add location

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
