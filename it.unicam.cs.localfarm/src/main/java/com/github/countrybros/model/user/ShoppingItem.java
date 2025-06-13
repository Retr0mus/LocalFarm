package com.github.countrybros.model.user;

import com.github.countrybros.model.product.Item;
import jakarta.persistence.*;

/**
 * Represents an item in the shopping cart.
 */
@Entity
public class ShoppingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @Transient
    private Item item;
    private int quantity;

    public ShoppingItem(Cart cart, Item item, int quantity) {}

    public ShoppingItem() {

    }

    public Cart getCart() {
        return cart;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


