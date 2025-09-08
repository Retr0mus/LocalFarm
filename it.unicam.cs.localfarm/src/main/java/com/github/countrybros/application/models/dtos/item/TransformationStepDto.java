package com.github.countrybros.application.models.dtos.item;

import com.github.countrybros.model.utils.Location;

import java.util.ArrayList;

/**
 * DTO for @TransformationStep
 */
public class TransformationStepDto {

    public String description;

    public Location location;

    public ArrayList<String> ingredients = new ArrayList<>();

    public ArrayList<Integer> ingredientsIds = new ArrayList<>();
}
