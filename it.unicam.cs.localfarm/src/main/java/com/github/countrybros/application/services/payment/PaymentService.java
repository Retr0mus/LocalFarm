package com.github.countrybros.application.services.payment;

import com.github.countrybros.application.abstractions.IPaymentMethod;
import com.github.countrybros.application.errors.ExternalError;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.order.Order;
import com.github.countrybros.model.order.OrderItem;
import com.github.countrybros.model.order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service that performs all the tasks related to the management of the payment.
 */
@Service
public class PaymentService implements IPaymentService {

    IPaymentMethod paymentMethod;

    @Autowired
    public PaymentService(IPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Payment of debts about some companies
     */
    @Override
    public void paySellers(List<Order> orders) {

        Map<Company, List<OrderItem>> itemsToPay = new HashMap<>();

        for (Order order : orders) {
            if (order.getOrderStatus().equals(OrderStatus.delivered))
                for (OrderItem item : order.getItems()) {
                    if (item.isPaid())
                        continue;
                    Company company = item.getSeller();
                    itemsToPay.computeIfAbsent(company, c -> new ArrayList<>()).add(item);
                }
        }

        for (Company company : itemsToPay.keySet()) {
            double total = 0.0;
            List<OrderItem> items = itemsToPay.get(company);
            for (OrderItem item : items)
                total += item.getQuantity() * item.getUnitPrice();
            if (! paymentMethod.pay(Double.valueOf(total).floatValue(), company.getEmail()))
                throw new ExternalError("Payment failed for " + company.getEmail());
            for (OrderItem item : items)
                item.setPaid(true);
        }
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
            boolean refundSuccess = paymentMethod.pay(amount, email);
            if (!refundSuccess) {
                throw new IllegalStateException("Refund failed, order blocked");
            }
            return refundSuccess;
        } catch (Exception e) {
            throw new IllegalStateException("Refund service unavailable, order blocked");
        }
    }
};
