package com.github.countrybros.model.product;

import com.github.countrybros.model.user.Company;

/**
 * Class that represent the ownership of a stock of item by a company that wants to sell it.
 */
public class Item {
    private int id;
    private Company seller;
    private double price;
    private ItemDetails itemDetails;
    private int qty;

    public Company getSeller() {
        return seller;
    }

    public double getPrice() {
        return price;
    }

    public ItemDetails getItemDetails() {
        return itemDetails;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getId() {
        return id;
    }
}
