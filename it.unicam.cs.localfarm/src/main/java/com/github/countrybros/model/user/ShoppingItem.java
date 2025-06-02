package com.github.countrybros.model.user;

import com.github.countrybros.model.product.Item;

/**
 * Represents an item in the shopping cart.
 */
public class ShoppingItem {
    private Cart cart;
    private Item item;
    private int quantity;

    public ShoppingItem(Cart cart, Item item, int quantity) {}

    public Cart getCart() {
        return cart;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}


