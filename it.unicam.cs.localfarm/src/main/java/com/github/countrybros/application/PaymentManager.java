package com.github.countrybros.application;

import com.github.countrybros.model.Order;
import com.github.countrybros.model.PaymentMethod;
import com.github.countrybros.model.ShippingAddress;

/**
 * Service that performs all the tasks related to the management of the payment.
 */
public class PaymentManager {
    private ShoppingManager shoppingManager;
    private UserManager userManager;

    /**
     * Initiates the checkout process for the specified user and shipping address.
     *
     * @param userId  the ID of the user
     * @param method payment method
     * @param address the shipping address
     *
     */
    public Order checkout(int userId, PaymentMethod method, ShippingAddress address) { return null; }


    public void paySeller() {}

}