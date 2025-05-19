package com.github.countrybros.infrastructure;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.model.Event;
import com.github.countrybros.model.User;

import java.util.List;

public interface IEventRepository {

    /**
     * Adds an event into the repository.
     *
     * @param event the event to add.
     * @return if the event was added or not.
     */
    public boolean addEvent(Event event);

    /**
     * Removes an event from the repository.
     *
     * @param eventId the identifier of the event to remove.
     * @return if the event was removed or not.
     */
    public boolean removeEvent(int eventId);

    /**
     * Modifies an event from the repository.
     *
     * @param event the modified event to upload in the repository (ID must be valid)
     * @return if the event was successfully modified.
     */
    public boolean editEvent(Event event);

    /**
     * Returns the event associated with the specified ID.
     *
     * @param eventId ID of the event.
     * @return the event requested.
     *
     * @throws NotFoundInRepositoryException if the event is not found.
     */
    public Event getEventById(int eventId);

    /**
     * Returns all the events in the website.
     *
     * @return a list with all the events.
     */
    public List<Event> getEvents();

    /**
     * Returns all the currently public events in the website.
     *
     * @return list of events with status PUBLIC.
     */
    public List<Event> getPublicEvents();

}
