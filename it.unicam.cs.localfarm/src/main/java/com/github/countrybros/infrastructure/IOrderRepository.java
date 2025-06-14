package com.github.countrybros.infrastructure;

import com.github.countrybros.model.user.Order;

import java.util.Date;
import java.util.List;

@Deprecated
public interface IOrderRepository extends IRepository<Order>{

    /**
     * Returns the orders of the user.
     *
     * @param customerId user ID.
     *
     * @return the list of his orders.
     */
    List<Order> findByCustomer(int customerId);

    /**
     * Returns all the order made since the specified date.
     *
     * @param orderDate The date.
     *
     * @return a list of all order selected.
     */
    List<Order> findByOrderDate(Date orderDate);
}
