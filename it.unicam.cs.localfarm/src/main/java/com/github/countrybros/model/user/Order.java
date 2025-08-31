package com.github.countrybros.model.user;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class that represents an order.
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User customer;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Embedded
    private ShippingAddress address;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    public Order() {
        items = new ArrayList<OrderItem>();
        setOrderDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        setOrderStatus(OrderStatus.picking);
    }

    public int getOrderId() {
        return orderId;
    }

    public User getCustomer() {
        return customer;
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

    public void setCustomer(User user) {
        this.customer = user;
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

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void addItem(OrderItem item) {
        getItems().add(item);
    }

    public float getTotal() {
        float total = 0;
        for (OrderItem item : getItems()) {
            total += item.getUnitPrice() * item.getQuantity();
        }
        return total;
    }

    public int getId() { return orderId; }
}
