package com.github.countrybros.application.models.requests.item;

import java.util.ArrayList;

/**
 * DTO for the request of adding a simpleProduct
 */
public class AddSimpleProductRequest extends AddItemRequest {

    public ArrayList<Integer> certificationIds;
}
