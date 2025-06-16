package com.github.countrybros.model.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to rapresent a user
 */
@Entity
@Table(name = "users")
public class User {

    //TODO: See if there is a better way to manage users than this plain object
    //TODO: see if it is possible to remove the password from the object, keppeing it only on a future DB

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String name;
    private String password;
    private String email;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<UserRole> roles = new ArrayList<>();
    @OneToOne
    @JsonIgnore
    private Cart cart;

    public User() {
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public int getUserId() {
        return userId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

