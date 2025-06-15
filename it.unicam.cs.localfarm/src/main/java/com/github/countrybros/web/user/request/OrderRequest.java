package com.github.countrybros.web.user.request;

import com.github.countrybros.model.user.OrderStatus;
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
