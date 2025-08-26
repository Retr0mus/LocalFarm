package com.github.countrybros.model.user;

import jakarta.persistence.*;

import java.util.List;

/**
 * Class that represents a cart.
 */
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private List<ShoppingItem> items;

    public float getTotalAmount() {
        return 0;
    }

    public List<ShoppingItem> getShoppingItems() {
        return items;
    }

    public ShoppingItem getShoppingItem(int stockId) {
        for (ShoppingItem item : items)
            if(item.getItem().getId() == stockId)
                return item;

        return null;
    }

    public void setItems(List<ShoppingItem> items) {
        this.items = items;
    }

    public void addItem(ShoppingItem item) {
        items.add(item);
    }

    public void removeItem(ShoppingItem item) {
        items.remove(item);
    }

    public void clearItems() {
        items.clear();
    }
}
