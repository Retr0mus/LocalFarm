package com.github.countrybros.application.models.dtos.item;

import com.github.countrybros.model.item.Certification;

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
