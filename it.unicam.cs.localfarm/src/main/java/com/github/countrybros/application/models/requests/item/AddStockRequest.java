package com.github.countrybros.application.models.requests.item;

/**
 * Represents a request for creating a new Item.
 */
public class AddStockRequest {

    public int sellerId;
    public double price;
    public int itemDetailsId;
    public int qty;
}
