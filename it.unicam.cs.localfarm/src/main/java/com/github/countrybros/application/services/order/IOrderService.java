package com.github.countrybros.application.services.order;

import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.order.Order;
import com.github.countrybros.model.user.User;
import com.github.countrybros.application.models.requests.order.RefundRequest;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Responsibility of managing the Orders
 */
public interface IOrderService {

    /**
     * Returns an order
     *
     * @param id    the required order's id
     * @return      the order required
     */
    Order getOrder(int id);

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
    List<Order> getOrdersSince(LocalDate date);

    /**
     * Saves an order in the repository.
     *
     * @param order The order to save.
     */
    void addOrder(Order order);

    /**
     * Sets an order as paid.
     *
     * @param id    the paid order.
     */
    void setAsPaid(int id);

    /**
     *
     * Cancel order in the repository
     *
     * @param request
     */

    void cancelOrder(RefundRequest request);

    void blockOrder(int orderId);
}
