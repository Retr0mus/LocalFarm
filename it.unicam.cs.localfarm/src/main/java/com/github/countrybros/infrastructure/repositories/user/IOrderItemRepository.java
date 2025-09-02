package com.github.countrybros.infrastructure.repositories.user;

import com.github.countrybros.model.order.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface IOrderItemRepository extends CrudRepository<OrderItem, Integer> {
}
