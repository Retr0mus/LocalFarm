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
    //TODO not OneToOne
    @OneToOne(cascade = CascadeType.ALL)
    private ItemDetails itemDetails;
    private int qty;

    public ItemDetailsQuantity() {}

    public ItemDetailsQuantity(ItemDetails item, int qty) {

        this.itemDetails = item;
        this.qty = qty;
    }

    public ItemDetails getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ItemDetails itemDetails) {
        this.itemDetails = itemDetails;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int quantity) {
        this.qty = quantity;
    }
}
