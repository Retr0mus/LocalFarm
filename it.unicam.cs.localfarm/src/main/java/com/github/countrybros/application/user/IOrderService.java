package com.github.countrybros.application.user;

import com.github.countrybros.model.user.IPaymentMethod;
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
