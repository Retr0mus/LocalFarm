package com.github.countrybros.application.user;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.repository.IOrderRepository;
import com.github.countrybros.model.user.*;
import com.github.countrybros.web.user.request.RefundRequest;
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
    @Autowired
    private IOrderRepository orderRepository;
    private IPaymentMethod paymentMethod;


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

    }

    public boolean refund(RefundRequest request){
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new NotFoundInRepositoryException("Order not found with ID " + request.getOrderId()));

        float amountToRefund = (float) order.getTotal();

        if(request.getEmail() == null || request.getEmail().isEmpty()) {
            System.out.println("Invalid data received");
            orderService.blockOrder(order.getId());
            return false;
        }

        System.out.println("Attempt to refund  " + amountToRefund + " to " + request.getEmail());

        boolean refundSuccess = false;
        try {
            refundSuccess = paymentMethod.refund(amountToRefund);
        } catch (Exception e) {
            System.out.println("impossible to contact payment service.");
            orderService.blockOrder(order.getId());
        }

        order.setOrderStatus(OrderStatus.cancelled);
        orderRepository.save(order);
        return refundSuccess;
    }
};