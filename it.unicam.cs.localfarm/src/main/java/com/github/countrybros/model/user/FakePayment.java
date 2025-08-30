package com.github.countrybros.model.user;

import com.github.countrybros.application.user.IPaymentMethod;
import org.springframework.stereotype.Component;

@Component
public class FakePayment implements IPaymentMethod {

    @Override
    public boolean pay(float price) {
        return true;
    }
    @Override
    public boolean refund(float price) {return true;}


}
