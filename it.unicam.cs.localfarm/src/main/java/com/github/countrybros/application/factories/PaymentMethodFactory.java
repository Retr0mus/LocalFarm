package com.github.countrybros.application.factories;

import com.github.countrybros.application.abstractions.IPaymentMethod;


public abstract class PaymentMethodFactory {

    public abstract IPaymentMethod createPaymentMethod();
}
