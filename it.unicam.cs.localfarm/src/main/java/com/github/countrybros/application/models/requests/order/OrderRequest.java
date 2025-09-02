package com.github.countrybros.application.models.requests.order;

import com.github.countrybros.model.order.OrderStatus;
import com.github.countrybros.model.user.ShippingAddress;

import java.util.Date;

public class OrderRequest {
    public int userId;
    public int cartId;
    public int sellerId;
    public ShippingAddress address;
    public OrderStatus orderStatus;
    public Date orderDate;

}
