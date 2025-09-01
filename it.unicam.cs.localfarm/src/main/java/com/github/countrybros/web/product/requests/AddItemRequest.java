package com.github.countrybros.web.product.requests;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * DTO for adding generic ItemDetails
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddBundleRequest.class, name = "bundle"),
        @JsonSubTypes.Type(value = AddSimpleProductRequest.class, name = "simpleProduct"),
        @JsonSubTypes.Type(value = AddTransformedProductRequest.class, name = "transformedProduct")
})
public abstract class AddItemRequest {

    @Size(min = 3, max = 50)
    public String name;

    @NotNull
    @Size(max = 500)
    public String description;

    @Positive
    public int producerId;

    @NotNull
    public String type;
}