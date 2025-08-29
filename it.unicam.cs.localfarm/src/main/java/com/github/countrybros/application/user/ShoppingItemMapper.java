package com.github.countrybros.application.user;

import com.github.countrybros.application.user.dto.ShoppingItemDto;
import com.github.countrybros.model.user.ShoppingItem;

public class ShoppingItemMapper {

    public static ShoppingItemDto toDto(ShoppingItem shoppingItem) {
        ShoppingItemDto dto = new ShoppingItemDto();
        dto.setItemId(shoppingItem.getStock().getItem().getId());
        dto.setItemName(shoppingItem.getStock().getItem().getName());
        dto.setSellerId(shoppingItem.getStock().getSeller().getId());
        dto.setSellerName(shoppingItem.getStock().getSeller().getName());
        dto.setQuantity(shoppingItem.getQuantity());
        return dto;
    }

}
