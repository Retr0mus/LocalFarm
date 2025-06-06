package com.github.countrybros.model.user;


import jakarta.persistence.*;

import java.util.List;

/**
 * Class to rapresent a user
 */
@Entity
public class User {

    //TODO: See if there is a better way to manage users than this plain object
    //TODO: see if it is possible to remove the password from the object, keppeing it only on a future DB

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserId;
    private String name;
    private String password;
    private String email;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private List<String> roles;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public User() {

    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public int getUserId() {
        return UserId;
    }
}

