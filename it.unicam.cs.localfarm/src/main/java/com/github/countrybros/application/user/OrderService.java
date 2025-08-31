package com.github.countrybros.application.user;

import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.repository.IOrderItemRepository;
import com.github.countrybros.infrastructure.repository.IOrderRepository;
import com.github.countrybros.model.user.*;
import com.github.countrybros.web.user.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IOrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(IOrderRepository orderRepository, IOrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    /**
     * Returns an order
     *
     * @param id the required order's id
     * @return the order required
     */
    @Override
    public Order getOrder(int id) {
        return orderRepository.findById(id).orElseThrow(() -> new ImpossibleRequestException("The order with ID " + id + " does not exist."));
    }

    @Override
    public List<Order> getOrders(User user) {
        return orderRepository.findOrderByCustomer(user);
    }

    @Override
    public List<Order> getOrdersSince(Date date) {

        return orderRepository.findOrderByOrderDate(date);
    }

    /**
     * Saves an order in the repository.
     *
     * @param order The order to save.
     */
    @Override
    public void addOrder(Order order) {
        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());
    }

    /**
     * Sets an order as paid.
     *
     * @param id the paid order.
     */
    @Override
    public void setAsPaid(int id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ImpossibleRequestException("The order with ID " + id + " does not exist."));
        order.setOrderStatus(OrderStatus.packing);
        orderRepository.save(order);
    }

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
