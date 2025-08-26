package com.github.countrybros.application.product.dto;

import com.github.countrybros.model.product.TransformationStep;

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
