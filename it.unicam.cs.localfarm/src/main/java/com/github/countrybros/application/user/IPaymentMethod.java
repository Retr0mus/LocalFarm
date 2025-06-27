package com.github.countrybros.application.user;

import com.github.countrybros.model.user.Company;
import com.github.countrybros.model.user.User;

/**
 * Represents the responsibilities of a generic payment service
 *
 * TODO: definire meglio la responsabilità.
 */
public interface IPaymentMethod {

    /**
     * Payment
     *
     * @param price The price to receive.
     */
    boolean pay(float price);
}
