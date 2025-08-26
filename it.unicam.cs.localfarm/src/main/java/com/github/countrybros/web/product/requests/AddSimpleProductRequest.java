package com.github.countrybros.web.product.requests;

import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

/**
 * DTO for the request of adding a simpleProduct
 */
public class AddSimpleProductRequest extends AddItemRequest {

    public ArrayList<Integer> certificationIds;
}
