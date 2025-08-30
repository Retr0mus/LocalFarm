package com.github.countrybros.application.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service that performs all the tasks related to the management of the payment.
 */
@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private IPaymentMethod paymentMethod;


    /**
     * The payment of a user.
     *
     * @param amount the amount to pay.
     */
    @Override
    public boolean paymentToMarketplace(IPaymentMethod paymentMethod, float amount) {

        return paymentMethod.pay(amount);
    }



    @Override
    public void paySellers() {

    }

    public boolean refund(String email, float amount) {

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email must be provided for refund");
        }

        try {
            boolean refundSuccess = paymentMethod.refund(amount);
            if (!refundSuccess) {
                throw new IllegalStateException("Refund failed, order blocked");
            }
            return refundSuccess;
        } catch (Exception e) {
            throw new IllegalStateException("Refund service unavailable, order blocked");
        }
    }
};