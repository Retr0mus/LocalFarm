package com.github.countrybros.infrastructure.repositories.event;

import com.github.countrybros.model.event.EventState;
import org.springframework.data.repository.CrudRepository;
import com.github.countrybros.model.event.Event;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEventRepository extends CrudRepository<Event, Integer> {

    Event getEventById(int id);

    List<Event> getAllByState(EventState state);
}

