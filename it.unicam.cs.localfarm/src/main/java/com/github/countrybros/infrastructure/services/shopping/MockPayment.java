package com.github.countrybros.infrastructure.services.shopping;

import com.github.countrybros.application.abstractions.IPaymentMethod;

public class MockPayment implements IPaymentMethod {
    /**
     * Payment to the marketplace
     *
     * @param price    The price to receive.
     */
    @Override
    public boolean pay(float price, String receiverEmail) {
        return true;
    }

}
