package com.github.countrybros.application.user;

import com.github.countrybros.application.errors.FoundInRepositoryException;
import com.github.countrybros.application.errors.NotEnoughItemsException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.repository.ICartRepository;
import com.github.countrybros.infrastructure.repository.ICompanyRepository;
import com.github.countrybros.infrastructure.repository.IOrderRepository;
import com.github.countrybros.model.user.*;
import com.github.countrybros.web.user.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IShoppingService shoppingService;
    @Autowired
    private IUserService userService;
    private IPaymentService paymentService;
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private ICompanyRepository companyRepository;

    @Override
    public List<Order> getOrders(User user) {
        return orderRepository.findOrderByCustomer(user);
    }

    @Override
    public Order checkout(int userId, IPaymentMethod method, ShippingAddress address) {

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
        order.setCustomer(user);
        order.setOrderDate(new Date());
        order.setOrderStatus(OrderStatus.picking);
        orderRepository.save(order);
        return order;
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
        User user = userService.getUser(request.userId);
        Cart cart = cartRepository.findById(request.cartId)
                .orElseThrow(() -> new NotFoundInRepositoryException("Cart not found with ID " + request.cartId));

        Company seller = companyRepository.getCompaniesById(request.sellerId);
        if (seller == null) throw new NotFoundInRepositoryException("Seller not found with ID " + request.sellerId);

        Order order = new Order();
        order.setCustomer(user);
        order.setCart(cart);
        order.setSeller(seller);
        order.setAddress(request.address);
        order.setOrderDate(new Date());
        order.setOrderStatus(request.orderStatus != null ? request.orderStatus : OrderStatus.picking);

        if (orderRepository.existsById(order.getOrderId())) {
            throw new FoundInRepositoryException("Order already exists");
        }
        orderRepository.save(order);
    }

}
