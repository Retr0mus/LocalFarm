package com.github.countrybros.infrastructure.shopping;

import com.github.countrybros.application.user.dto.PaymentMethod;

public class MockPayment extends PaymentMethod {
    /**
     * Payment to the marketplace
     *
     * @param price    The price to receive.
     */
    @Override
    public boolean pay(float price) {
        return true;
    }
}
