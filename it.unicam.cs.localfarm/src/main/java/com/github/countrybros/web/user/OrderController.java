package com.github.countrybros.web.user;

import com.github.countrybros.application.Orchestrator;
import com.github.countrybros.application.user.IOrderService;
import com.github.countrybros.application.user.OrderService;
import com.github.countrybros.model.user.Order;
import com.github.countrybros.model.user.User;
import com.github.countrybros.web.user.request.CheckoutRequest;
import com.github.countrybros.web.user.request.OrderRequest;
import com.github.countrybros.web.user.request.RefundRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private Orchestrator orchestrator;

    @PostMapping("/orderList")
    public ResponseEntity<List<Order>> getOrders(@RequestParam int userId) {
        List<Order> orders = orchestrator.getOrders(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addOrder(@RequestBody OrderRequest request) {
        orchestrator.addOrder(request);
        return new ResponseEntity<>("Order added", HttpStatus.CREATED);
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelOrder(@RequestBody RefundRequest request) {
        orchestrator.cancelAndRefundOrder(request);
        return new ResponseEntity<>("Cancel request processed", HttpStatus.OK);
    }


}
