package com.github.countrybros.web.user;

import com.github.countrybros.application.user.IPaymentMethod;
import com.github.countrybros.model.user.ShippingAddress;

/**
 * DTO representing the checkout request.
 */
public class CheckoutRequest {

    private IPaymentMethod paymentMethod;
    private ShippingAddress shippingAddress;

    public IPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }
}
