package com.github.countrybros.model.user;

import jakarta.persistence.Embeddable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
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
    private Map<Integer, ShoppingItem> items = new HashMap<>();

    public boolean containsItem(int itemId){
        return false;
    }

    public float getTotalAmount() {
        return 0;
    }

    public Map<Integer, ShoppingItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<ShoppingItem> excessItems) {
    }


}
