package com.github.countrybros.application.models.requests.user;

import com.github.countrybros.application.abstractions.IPaymentMethod;
import com.github.countrybros.model.user.ShippingAddress;

/**
 * DTO representing the checkout request.
 */

public class CheckoutRequest {
    public IPaymentMethod paymentMethod;
    public ShippingAddress shippingAddress;
}
