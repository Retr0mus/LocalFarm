package com.github.countrybros.web.user.request;

import com.github.countrybros.model.user.IPaymentMethod;
import com.github.countrybros.model.user.ShippingAddress;

/**
 * DTO representing the checkout request.
 */

public class CheckoutRequest {

    public IPaymentMethod paymentMethod;
    public ShippingAddress shippingAddress;


}
