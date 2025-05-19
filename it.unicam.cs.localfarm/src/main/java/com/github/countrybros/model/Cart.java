package com.github.countrybros.model;

import java.util.Map;

/**
 * Class that represents an cart.
 */
public class Cart {
    private Map<Integer, ShoppingItem> items;

    public boolean containsItem(int itemId){
        return false;
    }
}
