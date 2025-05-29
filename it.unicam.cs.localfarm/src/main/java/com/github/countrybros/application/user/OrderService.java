package com.github.countrybros.application.user;

import com.github.countrybros.infrastructure.IOrderRepository;
import com.github.countrybros.model.user.*;

import java.util.Date;
import java.util.List;

public class OrderService implements IOrderService {

    //TODO: update with IUserService
    private ShoppingService shoppingService;
    //TODO: update with IUserService
    private UserService userService;

    private IOrderRepository orderRepository;

    @Override
    public List<Order> getOrders(int userId) {

        return orderRepository.findByCustomer(userId);
    }

    @Override
    public Order checkout(int userId, IPaymentMethod method, ShippingAddress address) {

        Cart cart = shoppingService.getCart(userId);

        //TODO: continue checkout
        //if (shoppingManager.)
        User user = userService.getUser(userId);
        Order order = new Order();

        order.setCart(cart);
        order.setAddress(address);
        order.setUser(user);
        return order;
    }

    @Override
    public List<Order> getOrdersSince(Date date) {
        return List.of();
    }
}
