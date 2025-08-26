package com.github.countrybros.infrastructure.shopping;

import com.github.countrybros.application.user.PaymentMethodFactory;
import com.github.countrybros.application.user.dto.PaymentMethod;

public class MockPaymentFactory extends PaymentMethodFactory {

    @Override
    public PaymentMethod createPaymentMethod() {
        return new MockPayment();
    }
}
