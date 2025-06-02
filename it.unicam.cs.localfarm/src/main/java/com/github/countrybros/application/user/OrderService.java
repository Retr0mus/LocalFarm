package com.github.countrybros.application.user;

import com.github.countrybros.application.errors.FoundInRepositoryException;
import com.github.countrybros.application.errors.NotEnoughItemsException;
import com.github.countrybros.infrastructure.IOrderRepository;
import com.github.countrybros.model.user.*;

import java.util.Date;
import java.util.List;

public class OrderService implements IOrderService {

    //TODO: update with IUserService
    private ShoppingService shoppingService;
    //TODO: update with IUserService
    private UserService userService;
    private IPaymentService paymentService;

    private IOrderRepository orderRepository;

    @Override
    public List<Order> getOrders(int userId) {

        return orderRepository.findByCustomer(userId);
    }

    @Override
    public void checkout(int userId, IPaymentMethod method, ShippingAddress address) {

        Cart cart = shoppingService.getCart(userId);
        Cart excessCart = shoppingService.getExcessItems(cart);

        if (excessCart.getItems().isEmpty())
            throw new NotEnoughItemsException("Item quantity not available", excessCart);

        //TODO: User as parameter, maybe
        paymentService.buy(userId, method, cart.getTotalAmount());

        User user = userService.getUser(userId);
        Order order = new Order();
        order.setCart(cart);
        order.setAddress(address);
        order.setUser(user);
        this.addOrder(order);
    }

    @Override
    public List<Order> getOrdersSince(Date date) {

        return orderRepository.findByOrderDate(date);
    }

    /**
     * Saves an order in the repository.
     *
     * @param order The order to save.
     */
    private void addOrder(Order order) {

        if (orderRepository.exists(order.getOrderId()))
            throw new FoundInRepositoryException("Order already exists");

        orderRepository.save(order);
    }
}
