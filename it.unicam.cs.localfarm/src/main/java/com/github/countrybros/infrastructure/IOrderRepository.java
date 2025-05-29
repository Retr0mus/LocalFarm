package com.github.countrybros.infrastructure;

import com.github.countrybros.model.user.Order;

import java.util.List;

public interface IOrderRepository extends IRepository<Order>{

    /**
     * Returns the orders of the user.
     *
     * @param customerId user ID.
     *
     * @return the list of his orders.
     */
    List<Order> findByCustomer(int customerId);
}
