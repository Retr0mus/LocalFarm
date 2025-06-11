package com.github.countrybros.web.product.requests;

import com.github.countrybros.model.product.Certification;

import java.util.ArrayList;

/**
 * DTO for the request of adding a simpleProduct
 */
public class AddSimpleProductDetailsRequest extends AddItemDetailsRequest {

    //TODO: use IDs, not actual objects
    public ArrayList<Certification> certifications;
}
