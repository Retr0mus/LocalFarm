package com.github.countrybros.application.models.requests.item;

import jakarta.validation.constraints.Size;

/**
 * Request to add a new certification
 */
public class AddCertificationRequest {

    @Size(min = 3, max = 50, message = "Name must be between 3 and 50")
    public String name;

    @Size(max = 500, message = "max description character is 500")
    public String description;
}
