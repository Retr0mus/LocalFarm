package com.github.countrybros.application.user;

import com.github.countrybros.model.user.Order;
import com.github.countrybros.web.user.request.OrderRequest;
import com.github.countrybros.web.user.request.RefundRequest;

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
    List<Order> getOrders(int user);

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

    /**
     *
     * Cancel order in the repository
     *
     * @param request
     */

    void cancelOrder(RefundRequest request);

    /**
     *
     * Block order when the payment doesn't go the right way
     *
     * @param orderId
     */
    void blockOrder(int orderId);
}
