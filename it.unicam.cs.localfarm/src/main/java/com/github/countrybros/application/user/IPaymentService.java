package com.github.countrybros.application.user;

import com.github.countrybros.model.user.IPaymentMethod;

import java.util.Date;

/**
 * Responsibility of managing all the payment that can occur through the system.
 */
public interface IPaymentService {

    /**
     * The payment of a user.
     *
     * @param userId        ID of the user.
     * @param paymentMethod method chosen by the user.
     * @param amount        the amount to pay.
     */
    boolean buy(int userId, IPaymentMethod paymentMethod, float amount);

    /**
     * Payment of all the orders delivered by the companies since
     * the specified date.
     */
    void paySellers(Date dateSince);
}
