package com.github.countrybros.model.user;

import com.github.countrybros.model.product.Item;
import jakarta.persistence.Embeddable;

/**
 * Represents an item in the shopping cart.
 */
@Embeddable
public class ShoppingItem {
    private Cart cart;
    private Item item;
    private int quantity;
}


