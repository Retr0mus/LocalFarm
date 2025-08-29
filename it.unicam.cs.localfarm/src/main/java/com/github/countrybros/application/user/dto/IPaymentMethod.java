package com.github.countrybros.application.user.dto;

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
