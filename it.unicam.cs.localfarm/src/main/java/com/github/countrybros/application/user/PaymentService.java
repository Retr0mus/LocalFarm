package com.github.countrybros.application.user;

import com.github.countrybros.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 * Service that performs all the tasks related to the management of the payment.
 */
@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private IUserService userService;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IOrderService orderService;


    /**
     * The payment of a user.
     *
     * @param amount the amount to pay.
     */
    @Override
    public boolean paymentToMarketplace(IPaymentMethod paymentMethod, float amount) {

        return paymentMethod.pay(amount);
    }

    @Override
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
            for (ShoppingItem item : order.getCart().getItems().values()) {
                paySeller(item.getItem().getSeller().getId(), (float) (item.getQuantity() * item.getItem().getPrice()));
            }
        }
    };


    private void paySeller(int companyId, float paymentAmount) {

        Company company = companyService.getCompany(companyId);

        //TODO: manage method of the company
        //IPaymentMethod method = company.getPaymentMethod()
    }
}