package com.github.countrybros.application.user;

/**
 * Service that performs all the tasks related to the management of the payment.
 */
public class PaymentService implements IPaymentService {

    //TODO: da sostituire con l'interfaccia
    private ShoppingService shoppingService;
    //TODO: da sostituire con l'interfaccia
    private UserService userService;
    private ICompanyService companyService;
    private IOrderService orderService;

    @Override
    public boolean buy(int userId, IPaymentMethod paymentMethod) {
        return false;
    }

    @Override
    public void paySellers() {
        return;
    };

}