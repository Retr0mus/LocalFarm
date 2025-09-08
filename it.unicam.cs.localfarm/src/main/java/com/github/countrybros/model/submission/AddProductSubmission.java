package com.github.countrybros.model.submission;

import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.item.Item;
import jakarta.persistence.*;

/**
 * Represents a submission to add a new item for acceptance.
 */
@Entity
@DiscriminatorValue("addProduct")
public class AddProductSubmission extends Submission {

    @ManyToOne
    private Item item;

    public AddProductSubmission() {

    }

    public AddProductSubmission(Company company, Item item) {
        super(company);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
