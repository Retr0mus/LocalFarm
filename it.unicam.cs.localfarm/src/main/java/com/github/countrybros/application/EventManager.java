package com.github.countrybros.application;

import com.github.countrybros.model.Event;
import com.github.countrybros.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service that performs all the tasks related to the management of the events.
 */
public class EventManager {

    private static final EventManager instance = new EventManager();
    private final Map<Integer, Event> eventRepository = new HashMap<Integer, Event>();

    public static EventManager getInstance() {return instance;}

    /**
     * Returns all the events in the website.
     *
     * @return a list with all the events.
     */
    public List<Event> getEvents() {
        return new ArrayList<>(eventRepository.values());
    }

    /**
     * Returns the event specified in the request.
     *
     * @param eventId the requested event.
     * @return the event.
     */
    public Event getEventById(int eventId) {
        return eventRepository.get(eventId);
    }

    /**
     * Adds an event into the repository.
     *
     * @param event the event to add.
     * @return if the event was added or not.
     */
    public boolean addEvent(Event event) {

        if (eventRepository.containsKey(event.getId())) {
            return false;
        }
        eventRepository.put(eventRepository.size(), event);
        return true;
    }

    /**
     * Removes an event from the repository.
     *
     * @param eventId the identifier of the event to subscribe to.
     * @return if the event was removed or not.
     */
    public boolean removeEvent(int eventId) {
        if (!eventRepository.containsKey(eventId)){
            return false;
        }
        eventRepository.remove(eventId);
        return true;
    }

    /**
     * Subscribes a user on an event.
     *
     * @param user the user who wants to subscribe.
     * @param eventId the identifier of the event to subscribe to.
     * @return if the operation succeeded or not.
     */
    public boolean subscribeOnEvent(User user, int eventId) {
        return getEventById(eventId).subscribe(user);
    }

    /**
     * Unsubscribes a user on an event.
     *
     * @param user the user who wants to unsubscribe.
     * @param eventId the identifier of the event to unsubscribe to.
     * @return if the operation succeeded or not.
     */
    public boolean unsubscribeOnEvent(User user, int eventId) {
        return getEventById(eventId).unsubscribe(user);
    }

}
