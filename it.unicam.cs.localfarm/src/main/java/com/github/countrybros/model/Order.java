package com.github.countrybros.model;

import java.util.Date;
import java.util.Map;

/**
 * Class to represent a order.
 */
public class Order {
    private int orderId;
    //private Map<Item, Integer> items;
    private Date orderDate;
    private String orderStatus;
    private double totalAmount;

    public int getOrderId() {
        return orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    //TODO add getItems

    public String getOrderStatus() {
        return orderStatus;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}