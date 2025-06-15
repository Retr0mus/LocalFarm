package com.github.countrybros.web.user.request;



import com.github.countrybros.model.user.UserRole;

import java.util.List;

public class AddUserRequest {
    public int userId;
    public String name;
    public String password;
    public String email;
    public List<UserRole> roles;


}
