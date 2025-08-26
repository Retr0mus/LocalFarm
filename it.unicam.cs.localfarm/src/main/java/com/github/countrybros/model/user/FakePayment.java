package com.github.countrybros.model.user;

import com.github.countrybros.application.user.IPaymentMethod;

public class FakePayment implements IPaymentMethod {

    @Override
    public boolean pay(float price) {
        return true;
    }
    @Override
    public boolean refund(float price) {return true;}


}
