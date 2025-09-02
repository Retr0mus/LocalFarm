package com.github.countrybros.application.services.payment;

import com.github.countrybros.application.abstractions.IPaymentMethod;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.order.Order;

import java.util.List;
import java.util.Map;

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
     * Payment of debts about some companies
     */
    void paySellers(Map<Company, Double> mappedDebts);

    List<IPaymentMethod> getPaymentMethods();

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
