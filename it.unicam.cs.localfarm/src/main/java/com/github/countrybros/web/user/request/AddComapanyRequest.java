package com.github.countrybros.web.user.request;

public class AddComapanyRequest {
    private String name;
    private String email;
    private String password;
    private String description;
    //TODO location


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
