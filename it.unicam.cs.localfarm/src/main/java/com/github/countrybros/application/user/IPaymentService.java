package com.github.countrybros.application.user;

/**
 * Responsibility of managing all the payment that can occur through the system.
 */
public interface IPaymentService {

    /**
     * The payment of a user.
     *
     * @param userId ID of the user.
     * @param paymentMethod method chosen by the user.
     *
     * @return true if success.
      */
    boolean buy(int userId, IPaymentMethod paymentMethod);

    /**
     * Payment of all the orders delivered by the companies.
     * TODO: mettere un parametro che indichi quanto indietro si vogliono recuperare gli ordini
     */
    void paySellers();
}
