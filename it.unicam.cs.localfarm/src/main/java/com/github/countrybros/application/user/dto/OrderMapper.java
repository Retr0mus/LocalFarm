package com.github.countrybros.application.user.dto;

import com.github.countrybros.model.user.Order;
import com.github.countrybros.model.user.OrderItem;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setOrderId(order.getOrderId());
        dto.setUserId(order.getCustomer().getId());
        dto.setUserName(order.getCustomer().getName());
        dto.setOrderDate(order.getOrderDate());
        dto.setOrderStatus(order.getOrderStatus().name());
        dto.setAddress(order.getAddress());
        dto.setItems(order.getItems().stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList()));
        dto.setTotal(order.getTotal());
        return dto;
    }

    private static OrderItemDto toDto(OrderItem item) {
        OrderItemDto dto = new OrderItemDto();
        dto.setItemId(item.getItem().getId());
        dto.setItemName(item.getItem().getName());
        dto.setSellerId(item.getSeller().getId());
        dto.setSellerName(item.getSeller().getName());
        dto.setQuantity(item.getQuantity());
        dto.setUnitPrice(item.getUnitPrice());
        return dto;
    }


}
