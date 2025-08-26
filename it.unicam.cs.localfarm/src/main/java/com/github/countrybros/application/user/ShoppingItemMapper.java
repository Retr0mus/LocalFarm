package com.github.countrybros.application.user;

import com.github.countrybros.application.user.dto.ShoppingItemDto;
import com.github.countrybros.model.user.ShoppingItem;

public class ShoppingItemMapper {

    public static ShoppingItemDto toDto(ShoppingItem shoppingItem) {
        ShoppingItemDto dto = new ShoppingItemDto();
        dto.setItemId(shoppingItem.getItem().getItem().getId());
        dto.setItemName(shoppingItem.getItem().getItem().getName());
        dto.setSellerId(shoppingItem.getItem().getSeller().getId());
        dto.setSellerName(shoppingItem.getItem().getSeller().getName());
        dto.setQuantity(shoppingItem.getQuantity());
        return dto;
    }

}
