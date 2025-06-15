package com.github.countrybros.web.product.requests;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * DTO for adding generic ItemDetails
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddBundleDetailsRequest.class, name = "bundle"),
        @JsonSubTypes.Type(value = AddSimpleProductDetailsRequest.class, name = "simpleProduct"),
        @JsonSubTypes.Type(value = AddTransformedProductDetailsRequest.class, name = "transformedProduct")
})
public abstract class AddItemDetailsRequest {

    public String name;

    public String description;

    public int producerId;

    public String type;
}