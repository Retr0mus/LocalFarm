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
    @OneToOne(cascade = CascadeType.ALL)
    private ItemDetails itemDetails;
    private int quantity;

    public ItemDetailsQuantity() {}

    public ItemDetailsQuantity(ItemDetails item, int quantity) {

        this.itemDetails = item;
        this.quantity = quantity;
    }

    public ItemDetails getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ItemDetails itemDetails) {
        this.itemDetails = itemDetails;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
