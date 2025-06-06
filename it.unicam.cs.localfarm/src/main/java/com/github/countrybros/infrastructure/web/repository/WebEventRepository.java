package com.github.countrybros.infrastructure.web.repository;

import org.springframework.data.repository.CrudRepository;
import com.github.countrybros.model.event.Event;
import org.springframework.stereotype.Repository;

@Repository
public interface WebEventRepository extends CrudRepository<Event, Integer> {

    Event getById(int id);
}

