package com.github.countrybros.application.models.requests.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class AddUserRequest {

    @Size(min = 3, max = 50, message = "Name length must be between 3 and 50.")
    public String name;
    @Size(min = 8, max = 50, message = "Password length must be between 8 and 50.")
    public String password;
    @NotNull
    @Email(message = "Invalid email format.")
    public String email;
    
}
