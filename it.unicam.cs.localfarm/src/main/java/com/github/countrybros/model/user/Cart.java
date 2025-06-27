package com.github.countrybros.model.user;

import jakarta.persistence.Embeddable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that represents a cart.
 */
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @OneToMany
    private List<ShoppingItem> items = new ArrayList<>();

    public boolean containsItem(int itemId){
        return false;
    }

    public float getTotalAmount() {
        return 0;
    }

    public List<ShoppingItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingItem> items) {
        this.items = items;
    }
}
