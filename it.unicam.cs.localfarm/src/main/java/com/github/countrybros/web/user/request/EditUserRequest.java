package com.github.countrybros.web.user.request;

import com.github.countrybros.model.user.UserRole;

import java.util.List;

public class EditUserRequest {
    public int userId;          // per identificare quale user modificare
    public String name;
    public String password;
    public String email;
    public List<UserRole> roles;
    public int cartId;


}
