package com.github.countrybros.application.user;


import com.github.countrybros.application.product.ItemManager;
import com.github.countrybros.model.user.Cart;
import com.github.countrybros.model.user.Order;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the shopping cart and orders.
 */
public class ShoppingManager {
    private UserManager userManager;
    private ItemManager itemManager;

    public Cart getCart(int userId) { return null; }

    public boolean addItemToCart(int userId, int itemId, int qty) { return false; }

    public boolean editQuantityOfItemInCart(int userId, int itemId, int newQuantity) { return false; }

    public boolean removeItemFromCart(int userId, int itemId, int qty) { return false; }

    public List<Order> getOrders(int userId) { return null; }

}