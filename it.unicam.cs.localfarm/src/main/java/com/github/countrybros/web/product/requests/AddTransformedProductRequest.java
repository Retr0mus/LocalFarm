package com.github.countrybros.web.product.requests;

import com.github.countrybros.model.product.TransformationStep;
import com.github.countrybros.model.product.TransformationStepDTO;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;

/**
 * DTO for the request to add a transformedProduct
 */
public class AddTransformedProductRequest extends AddSimpleProductRequest {

    //TODO: @Size(min = 1)
    public ArrayList<TransformationStepDTO> steps;
}
