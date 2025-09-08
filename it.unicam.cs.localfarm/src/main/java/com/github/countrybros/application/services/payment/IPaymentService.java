package com.github.countrybros.application.services.payment;

import com.github.countrybros.application.abstractions.IPaymentMethod;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.order.Order;
import com.github.countrybros.model.order.OrderItem;

import java.util.List;
import java.util.Map;

/**
 * Responsibility of managing all the payment that can occur through the system.
 */
public interface IPaymentService {

    /**
     * Pay the debts about some orders.
     *
     * @param orders the orders to pay
     * @return the map of companies with the orderItems paid
     */
    Map<Company, List<OrderItem>> paySellers(List<Order> orders);

    /**
     * The payment towards the platform.
     *
     * @param paymentMethod method chosen by the user.
     * @param amount        the amount to pay.
     */
    boolean paymentToMarketplace(IPaymentMethod paymentMethod, float amount);

    /**
     *
     * Refund
     *
     * @param email
     * @param amount
     * @return
     */
    boolean refund(String email,float amount);

}
