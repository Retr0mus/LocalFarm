package com.github.countrybros.model.product;

import com.github.countrybros.model.user.Company;
import jakarta.persistence.*;

/**
 * Class that represent the ownership of a stock of item by a company that wants to sell it.
 */
@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Company seller;
    private double price;
    @ManyToOne
    private Item item;
    private int qty;

    public void setSeller(Company seller) {
        this.seller = seller;
    }

    public void setItemDetails(Item item) {
        this.item = item;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Company getSeller() {
        return seller;
    }

    public double getPrice() {
        return price;
    }

    public Item getItemDetails() {
        return item;
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
