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
     * Retribution of a company.
     *
     * @param company The company.
     *
     * @param price The price to receive.
     */
    boolean pay(Company company, float price);

    /**
     * The payment of the user to the system.
     *
     * @param user The user.
     *
     * @param price The price to pay.
     */
    boolean buy(User user, float price);
}
}
