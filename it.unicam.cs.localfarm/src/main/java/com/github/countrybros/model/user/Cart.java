package com.github.countrybros.model.user;

import jakarta.persistence.Embeddable;

import java.util.Map;

/**
 * Class that represents a cart.
 *
 * TODO: remove embeddable? and make items back
 *
 */
@Embeddable
public class Cart {

    //private Map<Integer, ShoppingItem> items;

    public boolean containsItem(int itemId){
        return false;
    }
}
