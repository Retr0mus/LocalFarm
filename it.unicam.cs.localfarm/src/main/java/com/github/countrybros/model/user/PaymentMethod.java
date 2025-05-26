package com.github.countrybros.model.user;

/**
 * Interface for payment methods.
 */
public interface PaymentMethod {
    /**
     * Processes a payment for the specified user and amount.
     *
     * @param userId the ID of the user
     * @param price  the amount to be paid
     */
    void pay(int userId, float price);
}
