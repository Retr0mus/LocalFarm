package com.github.countrybros.web.user.request;



import com.github.countrybros.model.user.UserRole;

import java.util.List;

public class AddUserRequest {
    private int userId;
    private String name;
    private String password;
    private String email;
    private List<UserRole> roles;

    public int getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

}
