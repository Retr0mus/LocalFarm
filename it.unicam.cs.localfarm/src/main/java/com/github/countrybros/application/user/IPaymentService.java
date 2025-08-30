package com.github.countrybros.application.user;

import com.github.countrybros.web.user.request.RefundRequest;

/**
 * Responsibility of managing all the payment that can occur through the system.
 */
public interface IPaymentService {

    /**
     * The payment towards the platform.
     *
     * @param paymentMethod method chosen by the user.
     * @param amount        the amount to pay.
     */
    boolean paymentToMarketplace(IPaymentMethod paymentMethod, float amount);

    /**
     * Payment of all the orders delivered by the companies, cover 28 days
     */
    void paySellers();

    /**
     *
     * Refund
     *
     * @param email
     * @param amount
     * @return
     */
    boolean refund(String email,float amount);


}
