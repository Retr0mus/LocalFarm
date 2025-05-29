package com.github.countrybros.model.user;

import java.util.Date;

/**
 * Class to represent a order.
 */

/**
 * Class that represents an order.
 */
public class Order {
    private int orderId;
    private User user;
    private Cart cart;
    private Date orderDate;
    private OrderStatus orderStatus;
    private ShippingAddress address;

    public Order() {

        //TODO: date of today
        setOrderDate(new Date());
        setOrderStatus(OrderStatus.picking);
    }

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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public double getTotalAmount() {
        return cart.getTotalAmount();
    }

    public ShippingAddress getAddress() {
        return address;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setAddress(ShippingAddress address) {
        this.address = address;
    }
}
