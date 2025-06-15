package com.github.countrybros.web.user;

import com.github.countrybros.application.user.IOrderService;
import com.github.countrybros.model.user.Order;
import com.github.countrybros.model.user.User;
import com.github.countrybros.web.user.request.CheckoutRequest;
import com.github.countrybros.web.user.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/list")
    public ResponseEntity<List<Order>> getOrders(@RequestBody User user) {
        List<Order> orders = orderService.getOrders(user);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addOrder(@RequestBody OrderRequest request) {
        orderService.addOrder(request);
        return new ResponseEntity<>("Order added", HttpStatus.CREATED);
    }

}
