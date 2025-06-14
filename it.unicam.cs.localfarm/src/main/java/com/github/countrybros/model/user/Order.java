package com.github.countrybros.model.user;


import jakarta.persistence.*;

import java.util.Date;


/**
 * Class that represents an order.
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User customer;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company seller;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Embedded
    private ShippingAddress address;

    public Order() {

        //TODO: date of today
        setOrderDate(new Date());
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

    public Company getSeller() {
        return seller;
    }

    public void setSeller(Company seller) {
        this.seller = seller;
    }
}
