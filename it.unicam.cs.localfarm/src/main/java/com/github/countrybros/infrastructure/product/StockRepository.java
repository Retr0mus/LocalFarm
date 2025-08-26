package com.github.countrybros.infrastructure.product;

import com.github.countrybros.model.product.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface for item data access and manipulation.
 */

@Repository
public interface StockRepository extends CrudRepository<Stock, Integer> {

    List<Stock> findAllBySeller_Id(int sellerId);

    List<Stock> findAllByItem_Id(int itemId);
}
