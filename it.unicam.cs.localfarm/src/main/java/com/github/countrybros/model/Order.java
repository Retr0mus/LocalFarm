package com.github.countrybros.model;

import java.util.Date;
import java.util.Map;

/**
 * Class to represent a order.
 */
import java.util.Date;

/**
 * Class that represents an order.
 */
public class Order {
    private int orderId;
    private User user;
    private Cart cart;
    private Date orderDate;
    private String orderStatus;
    private double totalAmount;
    private ShippingAddress address;

    public int getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public Cart getCart() {
        return cart;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public ShippingAddress getAddress() {
        return address;
    }
}
