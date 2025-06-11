package com.github.countrybros.web.product.requests;

import java.util.ArrayList;

/**
 * DTO for the request to add a bundle
 */
public class AddBundleDetailsRequest extends AddItemDetailsRequest {

    public ArrayList<IdQuantityWrapper> items;
}
