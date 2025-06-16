package com.github.countrybros.web.product.requests;

/**
 * DTO for request of editing an ItemDetails
 */
public class EditItemDetailsRequest {

    public int itemId;

    /**
     * id of a company
     */
    public int senderId;

    public AddItemDetailsRequest changesToItemDetails;
}
