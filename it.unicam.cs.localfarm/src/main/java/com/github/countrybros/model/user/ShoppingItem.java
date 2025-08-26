package com.github.countrybros.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.countrybros.model.product.Stock;
import jakarta.persistence.*;

/**
 * Represents an item in the shopping cart.
 */

@Entity
public class ShoppingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @OneToOne(fetch = FetchType.EAGER)
    private Stock stock;

    private int quantity;

    public ShoppingItem(Cart cart, Stock stock, int quantity) {}

    public ShoppingItem() {

    }

    public Cart getCart() {
        return cart;
    }

    public Stock getItem() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() { return id;}

    public int getAvailableStock() {
        return stock != null ? stock.getQty() : 0;
    }
}


