package com.github.countrybros.application;


import com.github.countrybros.model.Order;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the shopping cart and orders.
 */
public class ShoppingManager {
    private UserManager userManager;
    //TODO implement itemManager in class diagram first
    //private ItemManager itemManager;

    //TODO add getCart

    public boolean addItemToCart(int userId, int itemId, int qty) {
        return false;
    }

    public boolean removeItemFromCart(int userId, int itemId, int qty) {
        return false;
    }

    public List<Order> getOrders(int userId) {
        return null;
    }

}