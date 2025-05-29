package com.github.countrybros.infrastructure.local;

import com.github.countrybros.infrastructure.IOrderRepository;
import com.github.countrybros.model.user.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LocalOrderRepository implements IOrderRepository {

    private final HashMap<Integer, Order> repository = new HashMap<>();

    @Override
    public Order get(int id) {

        return repository.get(id);
    }

    @Override
    public void delete(int id) {

        repository.remove(id);
    }

    @Override
    public void save(Order obj) {

        repository.put(obj.getOrderId(), obj);
    }

    @Override
    public boolean exists(int id) {

        return repository.containsKey(id);
    }

    @Override
    public List<Order> getAll() {

        return new ArrayList<>(repository.values());
    }


    @Override
    public List<Order> findByCustomer(int userId) {

        ArrayList<Order> orders = new ArrayList<>(repository.values());
        orders.removeIf(o -> o.getUser().getId() == userId);
        return orders;
    }
}
