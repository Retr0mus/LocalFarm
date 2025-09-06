package com.github.countrybros.application.services.payment;

import com.github.countrybros.application.abstractions.IPaymentMethod;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the payment.
 */
@Service
public class PaymentService implements IPaymentService {


    IPaymentMethod paymentMethod;
    /**
     * Payment of all the orders delivered by the companies, cover 28 days
     */
    @Override
    public void paySellers() {

    }

    @Override
    public List<IPaymentMethod> getPaymentMethods() {
        return List.of();
    }

    /**
     * The payment of a user.
     *
     * @param amount the amount to pay.
     */
    @Override
    public boolean paymentToMarketplace(IPaymentMethod paymentMethod, float amount) {

        return paymentMethod.pay(amount);
    }



    /**  @Override
    public void paySellers() {

        ArrayList<Order> orders = new ArrayList<>(orderService
                .getOrdersSince(Date.from(
                        LocalDateTime.now()
                                .minusDays(28)
                                .atZone(ZoneId.systemDefault())
                                .toInstant()
                )));

        for (Order order : orders) {
            if (order.getOrderStatus() != OrderStatus.delivered)
                continue;
            for (ShoppingItem item : order.getCart().getShoppingItems()) {
                paySeller(item.getItem().getSeller().getId(), (float) (item.getQuantity() * item.getItem().getPrice()));
            }
        }
    }**/

    public boolean refund(String email, float amount) {

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email must be provided for refund");
        }

        try {
            boolean refundSuccess = paymentMethod.pay(amount);
            if (!refundSuccess) {
                throw new IllegalStateException("Refund failed, order blocked");
            }
            return refundSuccess;
        } catch (Exception e) {
            throw new IllegalStateException("Refund service unavailable, order blocked");
        }
    }
};
