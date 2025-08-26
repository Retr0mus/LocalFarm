package com.github.countrybros.application.user;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.repository.IOrderItemRepository;
import com.github.countrybros.infrastructure.repository.IOrderRepository;
import com.github.countrybros.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    private IOrderRepository orderRepository;
    private IOrderItemRepository orderItemRepository;

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
        return orderRepository.findById(id).get();
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
        Order order = orderRepository.findById(id).get();
        order.setOrderStatus(OrderStatus.packing);
        orderRepository.save(order);
    }


    /**
     * Saves an order in the repository.
     *
     * @param order The order to save.
    public void addOrder(Order order) {
        User user = userService.getUser(order.userId);
        Cart cart = cartService.getCartById(order.cartId);

        Company seller = companyService.getCompany(order.sellerId);
        if (seller == null) throw new NotFoundInRepositoryException("Seller not found with ID " + order.sellerId);

        Order order = new Order();
        order.setCustomer(user);
        *//*order.setCart(cart);
        order.setSeller(seller);*//* //TODO remove
        order.setAddress(order.address);
        order.setOrderDate(new Date());
        order.setOrderStatus(order.orderStatus != null ? order.orderStatus : OrderStatus.picking);

        orderRepository.save(order);
    }*/

}
