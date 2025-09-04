package com.github.countrybros.application.models.requests.item;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

/**
 * Represents a request for creating a new Item.
 */
public class AddStockRequest {
    private int sellerId;
    @Positive(message = "The price must be positive.")
    private double price;
    private int itemId;

    public AddStockRequest(int sellerId, double price, int itemId) {
        this.sellerId = sellerId;
        this.price = price;
        this.itemId = itemId;
    }

    public AddStockRequest() {}

    public int getSellerId() {
        return sellerId;
    }

    public double getPrice() {
        return price;
    }

    public int getItemId() {
        return itemId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
