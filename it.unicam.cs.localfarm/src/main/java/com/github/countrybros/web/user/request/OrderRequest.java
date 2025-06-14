package com.github.countrybros.web.user.request;

import com.github.countrybros.model.user.OrderStatus;
import com.github.countrybros.model.user.ShippingAddress;

import java.util.Date;

public class OrderRequest {
    private int userId;
    private int cartId;
    private int sellerId;
    private ShippingAddress address;
    private OrderStatus orderStatus;
    private Date orderDate;

    public int getUserId() {
        return userId;
    }
    public int getCartId() {
        return cartId;
    }
    public int getSellerId() {
        return sellerId;
    }
    public ShippingAddress getAddress() {
        return address;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
