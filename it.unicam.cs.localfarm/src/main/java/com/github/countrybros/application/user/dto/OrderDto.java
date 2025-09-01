package com.github.countrybros.application.user.dto;

import com.github.countrybros.model.user.ShippingAddress;

import java.util.Date;
import java.util.List;

public class OrderDto {
    private int orderId;
    private int userId;
    private String userName;
    private Date orderDate;
    private String orderStatus;
    private ShippingAddress address;
    private List<OrderItemDto> items;
    private double total;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ShippingAddress getAddress() {
        return address;
    }

    public void setAddress(ShippingAddress address) {
        this.address = address;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
