package com.github.countrybros.web.product.requests;

import jakarta.validation.constraints.Size;

/**
 * Request to add a new certification
 */
public class AddCertificationRequest {

    @Size(min = 3, max = 50)
    public String name;

    @Size(max = 500)
    public String description;
}
