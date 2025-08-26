package com.github.countrybros.application.user;

import com.github.countrybros.application.user.dto.IPaymentMethod;
import com.github.countrybros.model.user.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the payment.
 */
@Service
public class PaymentService implements IPaymentService {
    /**
     * The payment towards the platform.
     *
     * @param paymentMethod method chosen by the user.
     * @param amount        the amount to pay.
     */
    @Override
    public boolean paymentToMarketplace(IPaymentMethod paymentMethod, float amount) {
        return false;
    }

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
     *//*
    @Override
    public boolean paymentToMarketplace(IPaymentMethod paymentMethod, float amount) {

        return paymentMethod.pay(amount, );
    }

    @Override
    public void paySellers() {

        *//*ArrayList<Order> orders = new ArrayList<>(orderService
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
        }*//*
    }

    @Override
    public List<IPaymentMethod> getPaymentMethods() {
        return List.of();
    }

    private void paySeller(int companyId, float paymentAmount) {

        Company company = companyService.getCompany(companyId);

        //TODO: manage method of the company
        //IPaymentMethod method = company.getPaymentMethod()
    }
*/
}

