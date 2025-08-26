package com.github.countrybros.infrastructure.repository;

import com.github.countrybros.model.user.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface IOrderItemRepository extends CrudRepository<OrderItem, Integer> {
}
