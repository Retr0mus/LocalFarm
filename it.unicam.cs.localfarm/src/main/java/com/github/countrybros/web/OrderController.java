package com.github.countrybros.web;

import com.github.countrybros.application.user.IOrderService;
import com.github.countrybros.application.user.IPaymentMethod;
import com.github.countrybros.model.user.Order;
import com.github.countrybros.model.user.ShippingAddress;
import com.github.countrybros.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/list")
    public List<Order> getOrders(@RequestBody User user) {
        return orderService.getOrders(user);
    }

    @PostMapping("/checkout")
    public Order checkout(@RequestParam int userId,@RequestBody IPaymentMethod paymentMethod,@RequestBody ShippingAddress shippingAddress) {
        return orderService.checkout(userId,paymentMethod,shippingAddress);
    }

}
