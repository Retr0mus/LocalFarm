package com.github.countrybros.application.user;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

/**
 * Service that performs all the tasks related to the management of the payment.
 */
@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private IShoppingService shoppingService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IOrderService orderService;

    @Override
    public boolean buy(int userId, IPaymentMethod paymentMethod, float paymentAmount) {
        User user = userService.getUser(userId);
        if (user == null) {
            throw new NotFoundInRepositoryException("User with ID " + userId + " not found.");
        }

        try {
            //TODO inserire la logica di pagamento
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void paySellers(Date date) {

        ArrayList<Order> orders = new ArrayList<>(orderService.getOrdersSince(date));

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