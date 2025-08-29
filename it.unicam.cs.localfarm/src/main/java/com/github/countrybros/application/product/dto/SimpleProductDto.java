package com.github.countrybros.application.product.dto;

import com.github.countrybros.model.product.Certification;
import com.github.countrybros.model.product.ItemStatus;

import java.util.List;

public class SimpleProductDto extends ItemDto{
    private List<Certification> certifications;

    public SimpleProductDto(){
    }

    public List<Certification> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<Certification> certifications) {
        this.certifications = certifications;
    }
}
