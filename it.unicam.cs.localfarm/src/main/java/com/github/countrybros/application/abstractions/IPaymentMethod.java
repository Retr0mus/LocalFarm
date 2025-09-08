package com.github.countrybros.application.abstractions;

/**
 * Represents the responsibilities of a generic payment service, based on Email
 */
public interface IPaymentMethod {

    /**
     * Payment.
     *
     * @param price The price to receive.
     * @param receiver the email of the receiver.
     */
    boolean pay(float price, String receiver);

}
