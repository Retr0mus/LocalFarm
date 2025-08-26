package com.github.countrybros.application.product;

import com.github.countrybros.application.product.dto.StockDto;
import com.github.countrybros.model.product.Stock;

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
