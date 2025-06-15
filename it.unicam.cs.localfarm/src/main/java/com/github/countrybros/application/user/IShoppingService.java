package com.github.countrybros.application.user;

import com.github.countrybros.model.user.Cart;
import com.github.countrybros.model.user.IPaymentMethod;
import com.github.countrybros.model.user.Order;
import com.github.countrybros.model.user.ShippingAddress;

import java.util.List;

/**
 * Responsibilities of cart managing
 */
public interface IShoppingService {

     Cart getCart(int userId);

     void addItemToCart(int userId, int itemId, int qty);

     void editQuantityOfItemInCart(int userId, int itemId, int newQuantity);

     void removeItemFromCart(int userId, int itemId, int qty);

     List<Order> getOrders(int userId);

    /**
     * returns the list of Items that cannot be bought because there is not enough.
     *
     * @param cart The cart to check.
     *
     * @return the list of items that cannot be bought.
     */
     Cart getExcessItems(Cart cart);

    /**
     * Create an order when a user decides to buy the item inside his cart.
     *
     * @param userId The user.
     * @param method The method chosen by the user.
     * @param address The address chosen by the user.
     */
    Order checkout(int userId, IPaymentMethod method, ShippingAddress address);
}
