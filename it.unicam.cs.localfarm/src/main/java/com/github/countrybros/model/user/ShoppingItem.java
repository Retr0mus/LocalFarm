package com.github.countrybros.model.user;

import com.github.countrybros.model.product.Stock;
import jakarta.persistence.*;

/**
 * Represents an item in the shopping cart.
 */

@Entity
public class ShoppingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Stock stock;
    private int quantity;

    public ShoppingItem(Stock stock, int quantity) {
        this.stock = stock;
        this.quantity = quantity;
    }

    public ShoppingItem() {

    }

    public Stock getItem() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderItem toOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(stock.getItem());
        orderItem.setSeller(stock.getSeller());
        orderItem.setQuantity(quantity);
        orderItem.setUnitPrice(stock.getPrice());
        return orderItem;
    }

}