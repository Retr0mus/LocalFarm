package com.github.countrybros.model.product;

import jakarta.persistence.*;

/**
 * Represents an Item with a quantity.
 */
@Entity
public class ItemDetailsQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Item item;
    private int qty;

    public ItemDetailsQuantity() {}

    public ItemDetailsQuantity(Item item, int qty) {

        this.item = item;
        this.qty = qty;
    }

    public Item getItemDetails() {
        return item;
    }

    public void setItemDetails(Item item) {
        this.item = item;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int quantity) {
        this.qty = quantity;
    }
}
