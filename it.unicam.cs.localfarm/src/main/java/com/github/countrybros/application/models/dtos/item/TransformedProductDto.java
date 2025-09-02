package com.github.countrybros.application.models.dtos.item;

import com.github.countrybros.model.item.TransformationStep;

import java.util.List;

public class TransformedProductDto extends SimpleProductDto {
    private List<TransformationStep> transformationStepList;

    public TransformedProductDto() {
    }

    public List<TransformationStep> getTransformationStepList() {
        return transformationStepList;
    }

    public void setTransformationStepList(List<TransformationStep> transformationStepList) {
        this.transformationStepList = transformationStepList;
    }
}
