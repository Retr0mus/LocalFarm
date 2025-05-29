package com.github.countrybros.application.user;

import com.github.countrybros.model.user.Cart;
import com.github.countrybros.model.user.Order;

import java.util.List;

/**
 * Responsibilities of cart managing
 */
public interface IShoppingService {

    public Cart getCart(int userId);

    public boolean addItemToCart(int userId, int itemId, int qty);

    public boolean editQuantityOfItemInCart(int userId, int itemId, int newQuantity);

    public boolean removeItemFromCart(int userId, int itemId, int qty);

    public List<Order> getOrders(int userId);

    /**
     * returns the list of Items that cannot be bought because there is not enough.
     *
     * @param cart The cart to check.
     *
     * @return the list of items that cannot be bought.
     */
    public Cart getExcessItems(Cart cart);
}
