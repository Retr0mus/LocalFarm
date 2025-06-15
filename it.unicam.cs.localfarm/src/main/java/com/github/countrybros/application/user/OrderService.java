package com.github.countrybros.application.user;

import com.github.countrybros.application.errors.FoundInRepositoryException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
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
    private IUserService userService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IOrderRepository orderRepository;

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
     * @param request The order to save.
     */
    public void addOrder(OrderRequest request) {
        User user = userService.getUser(request.userId);
        Cart cart = cartService.getCartById(request.cartId);

        Company seller = companyService.getCompany(request.sellerId);
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
