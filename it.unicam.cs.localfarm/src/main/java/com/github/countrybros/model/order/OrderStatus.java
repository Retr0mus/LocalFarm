package com.github.countrybros.model.order;

/**
 * status of an @Order
 */
public enum OrderStatus {
    picking,
    packing,
    shipping,
    delivered,
    cancelled,
    blocked
}
