package com.github.countrybros.application.services.payment;

import com.github.countrybros.application.abstractions.IPaymentMethod;
import com.github.countrybros.application.errors.ExternalError;
import com.github.countrybros.infrastructure.services.shopping.MockPayment;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.order.Order;
import com.github.countrybros.model.order.OrderItem;
import com.github.countrybros.model.order.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service that performs all the tasks related to the management of the payment.
 */
@Service
public class PaymentService implements IPaymentService {


    IPaymentMethod paymentMethod;

    /**
     * Payment of debts about some companies
     */
    @Override
    public void paySellers(Map<Company, Double> mappedDebts) {

        MockPayment mockPayment = new MockPayment();
        for (Company company : mappedDebts.keySet())
            if (! mockPayment.pay(mappedDebts.get(company).floatValue(), company.getEmail()))
                throw new ExternalError("Payment failed for " + company.getEmail());

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

        return paymentMethod.pay(amount, "this.is@emailofSystem");
    }

    public boolean refund(String email, float amount) {

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email must be provided for refund");
        }

        try {
            boolean refundSuccess = paymentMethod.refund(amount);
            if (!refundSuccess) {
                throw new IllegalStateException("Refund failed, order blocked");
            }
            return refundSuccess;
        } catch (Exception e) {
            throw new IllegalStateException("Refund service unavailable, order blocked");
        }
    }
};
