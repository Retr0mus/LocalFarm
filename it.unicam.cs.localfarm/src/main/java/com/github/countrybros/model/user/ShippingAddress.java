package com.github.countrybros.model.user;

import jakarta.persistence.Embeddable;

/**
 * Class that represents a shipping address to which an order can be delivered.
 */
@Embeddable
public class ShippingAddress {
    private String state;
    private String province;
    private String street;
    private int streetNumber;
}
