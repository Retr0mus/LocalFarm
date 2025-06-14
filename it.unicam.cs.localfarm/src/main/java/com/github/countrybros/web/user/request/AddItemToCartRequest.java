package com.github.countrybros.web.user.request;

public class AddItemToCartRequest {
    private int userId;
    private int itemId;
    private int quantity;

    public int getUserId() {
        return userId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }
}
