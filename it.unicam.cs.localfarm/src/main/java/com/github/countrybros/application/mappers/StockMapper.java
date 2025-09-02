package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.dtos.item.StockDto;
import com.github.countrybros.model.stock.Stock;

public class StockMapper {

    public static StockDto toDto(Stock stock) {
        StockDto stockDto = new StockDto();

        stockDto.setId(stock.getId());
        stockDto.setPrice(stock.getPrice());
        stockDto.setQuantity(stock.getQty());
        stockDto.setItemId(stock.getItem().getId());
        stockDto.setItemName(stock.getItem().getName());
        stockDto.setSellerId(stock.getSeller().getId());
        stockDto.setSellerName(stock.getSeller().getName());

        return stockDto;
    }
}
