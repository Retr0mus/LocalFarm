package com.github.countrybros.application.user;


import com.github.countrybros.application.product.ItemManager;
import com.github.countrybros.model.user.Cart;
import com.github.countrybros.model.user.Order;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the shopping cart and orders.
 */
public class ShoppingService implements IShoppingService {
    //TODO: update with interface
    private UserService userService;
    //TODO: update with interface
    private ItemManager itemManager;

    @Override
    public Cart getCart(int userId) { return null; }

    @Override
    public boolean addItemToCart(int userId, int itemId, int qty) { return false; }

    @Override
    public boolean editQuantityOfItemInCart(int userId, int itemId, int newQuantity) { return false; }

    @Override
    public boolean removeItemFromCart(int userId, int itemId, int qty) { return false; }

    @Override
    public List<Order> getOrders(int userId) { return null; }

    @Override
    public Cart getExcessItems(Cart cart) {

        //TODO
        return null;
    }

}