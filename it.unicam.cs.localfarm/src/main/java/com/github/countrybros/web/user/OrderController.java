package com.github.countrybros.web.user;

import com.github.countrybros.application.Orchestrator;
import com.github.countrybros.application.user.IOrderService;
import com.github.countrybros.application.user.OrderService;
import com.github.countrybros.application.user.dto.OrderDto;
import com.github.countrybros.application.user.dto.OrderMapper;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private Orchestrator orchestrator;

    @GetMapping("/orderList")
    public ResponseEntity<List<OrderDto>> getOrders(@RequestParam int userId) {
        List<Order> orders = orchestrator.getOrders(userId);
        List<OrderDto> orderDtos = orders.stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    /*@PostMapping("/add")
    public ResponseEntity<String> addOrder(@RequestBody OrderRequest request) {
        orchestrator.addOrder(request);
        return new ResponseEntity<>("Order added", HttpStatus.CREATED);
    }*/

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelOrder(@RequestBody RefundRequest request) {
        try {
            orchestrator.cancelAndRefundOrder(request);
            return ResponseEntity.ok("Order cancelled & refunded");
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cannot cancel order: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to process cancel request");
        }
    }


}
