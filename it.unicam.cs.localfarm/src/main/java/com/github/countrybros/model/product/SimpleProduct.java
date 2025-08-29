package com.github.countrybros.model.product;

import com.github.countrybros.model.user.SocialPost;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Details for a simple product.
 */
@Entity
@DiscriminatorValue("simpleProduct")
public class SimpleProduct extends Item {

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Certification> certifications = new ArrayList<>();

    public List<Certification> getCertifications() { return certifications; }

    public void setCertifications(ArrayList<Certification> certifications) {

        this.certifications = certifications;
    }

    @Override
    @Transient
    public SocialPost getPost() {
        return new SocialPost(getName(), getDescription(), "link");
    }
}
