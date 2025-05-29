package com.github.countrybros.application.user;

import com.github.countrybros.model.user.Order;
import com.github.countrybros.model.user.ShippingAddress;

import java.util.Date;
import java.util.List;

/**
 * Responsibility of managing the Orders
 */
public interface IOrderService {

    /**
     * Returns all the Orders made by a user.
     *
     * @param userId The user.
     *
     * @return a list of orders.
     */
    List<Order> getOrders(int userId);

    /**
     * Create an order when a user decides to buy the item inside his cart.
     *
     * @param userId The user.
     * @param method The method chosen by the user.
     * @param address The address chosen by the user.
     *
     * @return the order.
     */
    Order checkout(int userId, IPaymentMethod method, ShippingAddress address);

    /**
     * Returns all the order made since the specified date.
     *
     * @param date The date.
     *
     * @return the list of the order.
     */
    List<Order> getOrdersSince(Date date);
}
