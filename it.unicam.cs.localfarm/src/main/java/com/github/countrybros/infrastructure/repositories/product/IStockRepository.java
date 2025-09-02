package com.github.countrybros.infrastructure.repositories.product;

import com.github.countrybros.model.item.Item;
import com.github.countrybros.model.stock.Stock;
import com.github.countrybros.model.company.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface for item data access and manipulation.
 */

@Repository
public interface IStockRepository extends CrudRepository<Stock, Integer> {

    List<Stock> findAllBySeller_Id(int sellerId);

    List<Stock> findAllByItem_Id(int itemId);

    Stock findByItemAndSeller(Item item, Company seller);
}
