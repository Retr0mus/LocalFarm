package com.github.countrybros.infrastructure.local;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.IEventRepository;
import com.github.countrybros.model.Event;
import com.github.countrybros.model.EventState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalEventRepository implements IEventRepository {

    private final Map<Integer, Event> events = new HashMap<>();

    /**
     * Adds an event into the repository.
     *
     * @param event the event to add.
     * @return if the event was added or not.
     */
    @Override
    public boolean addEvent(Event event) {
        if (events.containsKey(event.getId())) {
            return false;
        }
        events.put(events.size(), event);
        return true;
    }

    /**
     * Removes an event from the repository.
     *
     * @param eventId the identifier of the event to remove.
     * @return if the event was removed or not.
     */
    @Override
    public boolean removeEvent(int eventId) {
        if (!events.containsKey(eventId)){
            return false;
        }
        events.remove(eventId);
        return true;
    }

    /**
     * Modifies an event from the repository.
     *
     * @param event the modified event to upload in the repository (ID must be valid)
     * @return if the event was successfully modified.
     */
    @Override
    public boolean editEvent(Event event) {
        return false;
    }

    /**
     * Returns the event associated with the specified ID.
     *
     * @param eventId ID of the event.
     * @return the event requested.
     * @throws NotFoundInRepositoryException if the event is not found.
     */
    @Override
    public Event getEventById(int eventId) {
        if (!events.containsKey(eventId))
            throw new NotFoundInRepositoryException("Event not found");

        return events.get(eventId);
    }

    /**
     * Returns all the events in the website.
     *
     * @return a list with all the events.
     */
    @Override
    public List<Event> getEvents() {
        return new ArrayList<>(events.values());
    }

    /**
     * Returns all the currently public events in the website.
     *
     * @return list of events with status PUBLIC.
     */
    @Override
    public List<Event> getPublicEvents() {
        List<Event> publicEvents = new ArrayList<>();
        for (Event event : events.values()) {
            if (event.getState() == EventState.currentlyPublic) {
                publicEvents.add(event);
            }
        }
        return publicEvents;
    }
}
