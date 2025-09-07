package com.github.countrybros.web.user;

import com.github.countrybros.application.facades.Orchestrator;
import com.github.countrybros.application.models.dtos.order.OrderDto;
import com.github.countrybros.application.mappers.OrderMapper;
import com.github.countrybros.application.services.order.IOrderService;
import com.github.countrybros.model.order.Order;
import com.github.countrybros.application.models.requests.order.RefundRequest;
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
    @Autowired
    private IOrderService orderService;

    @GetMapping("/orderList")
    public ResponseEntity<List<OrderDto>> getOrders(@RequestParam int userId) {
        List<Order> orders = orderService.getOrders(userId);
        List<OrderDto> orderDtos = orders.stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

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
