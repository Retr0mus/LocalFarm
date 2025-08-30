package com.github.countrybros.application.user;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.repository.IOrderRepository;
import com.github.countrybros.model.user.*;
import com.github.countrybros.web.user.request.OrderRequest;
import com.github.countrybros.web.user.request.RefundRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class OrderService implements IOrderService {


    @Autowired
    private IOrderRepository orderRepository;


    @Override
    public List<Order> getOrders(int userId) {
        return orderRepository.findOrderByCustomer_UserId(userId);
    }

    @Override
    public List<Order> getOrdersSince(Date date) {

        return orderRepository.findOrderByOrderDate(date);
    }

    /**
     * Saves an order in the repository.
     *
     * @param request The order to save.
     */
    public void addOrder(OrderRequest request) {

    }


    public void cancelOrder(RefundRequest request) {

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new NotFoundInRepositoryException("Order not found with ID " + request.getOrderId()));

        if (order.getOrderStatus() == OrderStatus.cancelled) {
            throw new IllegalStateException("order already cancelled");
        }

        if(order.getOrderStatus() == OrderStatus.shipping || order.getOrderStatus() == OrderStatus.delivered ) {
            throw new IllegalStateException("shipped or delivered order");
        }

        order.setOrderStatus(OrderStatus.cancelled);
        orderRepository.save(order);
    }

    public void blockOrder(int orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundInRepositoryException("Order not found with ID " + orderId));

        order.setOrderStatus(OrderStatus.blocked);
        orderRepository.save(order);

    }

}
