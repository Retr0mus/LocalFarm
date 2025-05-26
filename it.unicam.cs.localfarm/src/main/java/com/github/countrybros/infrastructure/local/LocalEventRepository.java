package com.github.countrybros.infrastructure.local;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.IEventRepository;
import com.github.countrybros.model.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LocalEventRepository implements IEventRepository {

    private final Map<Integer, Event> events = new HashMap<>();

    @Override
    public void save(Event event) {

        this.events.put(event.getId(), event);
    }

    @Override
    public void delete(int eventId) {

        if (!events.containsKey(eventId))
            throw new NotFoundInRepositoryException("Event not found");

        events.remove(eventId);
    }

    @Override
    public Event get(int eventId) {

        if (!events.containsKey(eventId))
            throw new NotFoundInRepositoryException("Event not found");

        return events.get(eventId);
    }

    @Override
    public boolean exists(int eventId) {

        return events.containsKey(eventId);
    }

    @Override
    public ArrayList<Event> getAll() {

        return new ArrayList<>(events.values());
    }
}
