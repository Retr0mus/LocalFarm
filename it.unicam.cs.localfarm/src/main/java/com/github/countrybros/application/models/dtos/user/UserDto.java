package com.github.countrybros.application.models.dtos.user;

import java.util.List;

public class UserDto {
    private int userId;
    private String name;
    private String email;
    private List<String> roles;
    private String address;

    public UserDto(int userId, String name, String email, List<String> roles, String address) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
