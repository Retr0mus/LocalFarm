package com.github.countrybros.web.product.requests;

import com.github.countrybros.model.product.TransformationStep;

import java.util.ArrayList;

/**
 * DTO for the request to add a transformedProduct
 */
public class AddTransformedProductDetailsRequest extends AddSimpleProductDetailsRequest {

    public ArrayList<TransformationStep> steps;
}
