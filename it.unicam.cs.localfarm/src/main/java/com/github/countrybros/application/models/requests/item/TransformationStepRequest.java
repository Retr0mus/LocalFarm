package com.github.countrybros.application.models.requests.item;

import com.github.countrybros.model.utils.Location;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

/**
 * DTO for @TransformationStep
 */
public class TransformationStepRequest {

    @NotNull(message = "Description of step missing")
    public String description;

    @NotNull(message = "Location of step missing")
    public Location location;

    public ArrayList<Integer> ingredientsIds = new ArrayList<>();
}
