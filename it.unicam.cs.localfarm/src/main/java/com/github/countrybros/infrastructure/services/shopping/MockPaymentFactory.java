package com.github.countrybros.infrastructure.services.shopping;

import com.github.countrybros.application.abstractions.IPaymentMethod;
import com.github.countrybros.application.factories.PaymentMethodFactory;

public class MockPaymentFactory extends PaymentMethodFactory {

    @Override
    public IPaymentMethod createPaymentMethod() {
        return new MockPayment();
    }
}
