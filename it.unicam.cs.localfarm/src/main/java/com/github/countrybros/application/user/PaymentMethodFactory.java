package com.github.countrybros.application.user;

import com.github.countrybros.application.user.dto.PaymentMethod;

public abstract class PaymentMethodFactory {

    public abstract PaymentMethod createPaymentMethod();
}
