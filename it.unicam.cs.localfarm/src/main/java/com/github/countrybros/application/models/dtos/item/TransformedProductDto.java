package com.github.countrybros.application.models.dtos.item;

import java.util.ArrayList;
import java.util.List;

public class TransformedProductDto extends SimpleProductDto {

    public List<TransformationStepDto> transformationStepList = new ArrayList<>();
}
