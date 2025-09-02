package com.github.countrybros.application.models.requests.item;

/**
 * DTO for request of editing an ItemDetails
 */
public class EditItemDetailsRequest {

    public int itemId;

    /**
     * id of a company
     */
    public int senderId;

    public AddItemRequest changesToItemDetails;
}
