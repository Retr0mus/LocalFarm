package com.github.countrybros.application.models.requests.item;

import jakarta.validation.constraints.Size;

import java.util.ArrayList;

/**
 * DTO for the request to add a transformedProduct
 */
public class AddTransformedProductRequest extends AddSimpleProductRequest {

    @Size(min = 1, message = "Transformed products should have at least one step")
    public ArrayList<TransformationStepRequest> steps;
}
