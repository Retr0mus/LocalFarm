package com.github.countrybros.application.user;

import com.github.countrybros.model.user.Order;
import com.github.countrybros.model.user.ShippingAddress;
import com.github.countrybros.model.user.User;
import com.github.countrybros.web.user.request.OrderRequest;

import java.util.Date;
import java.util.List;

/**
 * Responsibility of managing the Orders
 */
public interface IOrderService {

    /**
     * Returns all the Orders made by a user.
     *
     * @param user The user.
     *
     * @return a list of orders.
     */
    List<Order> getOrders(User user);


    /**
     * Create an order when a user decides to buy the item inside his cart.
     *
     * @param userId The user.
     * @param method The method chosen by the user.
     * @param address The address chosen by the user.
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

    /**
     * Saves an order in the repository.
     *
     * @param request The order to save.
     */
    void addOrder(OrderRequest request);
}
