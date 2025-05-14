package com.github.countrybros.model.product;

import com.github.countrybros.model.Company;

/**
 * Class to represent a item.
 */
public class Item {
    private Company seller;
    private double price;
    private ItemDetails itemDetails;
    private int qta;

    public Company getSeller() {
        return seller;
    }

    public double getPrice() {
        return price;
    }

    public ItemDetails getItemDetails() {
        return itemDetails;
    }

    public int getQta() {
        return qta;
    }
}
