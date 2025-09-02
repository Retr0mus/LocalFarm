package com.github.countrybros.application.models.requests.item;

import jakarta.validation.constraints.Size;

import java.util.Map;

/**
 * DTO for the request to add a bundle
 */
public class AddBundleRequest extends AddItemRequest {

    @Size(min = 1)
    public Map<Integer, Integer> items;
}
