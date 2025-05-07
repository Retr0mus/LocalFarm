package com.github.countrybros.application;

import com.github.countrybros.model.Event;
import com.github.countrybros.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service that performs all the tasks releted to the managemnent of the events.
 */
public class EventManager {

    private Map<Integer, Event> eventRepository;

    /**
     * Returns all the events in the website.
     *
     * @return a list with all the events.
     */
    public List<Event> getEvents() {
        return new ArrayList<>(eventRepository.values());
    }

    /**
     * Adds an event into the repository
     *
     * @param event the event to add
     * @return if the event was added or not
     */
    public boolean addEvent(Event event) {

        if (eventRepository.containsKey(event.getId())) {
            return false;
        }
        eventRepository.put(eventRepository.size(), event);
        return true;
    }

    /**
     * Removes an event from the repository
     *
     * @param eventId the identifier of the event to subscribe to
     * @return
     */
    public boolean removeEvent(int eventId) {
        if (!eventRepository.containsKey(eventId)){
            return false;
        }
        eventRepository.remove(eventId);
        return true;
    }

    /**
     * Subscribes a user on an event
     *
     * @param user the user who wants to subscribe
     * @param eventId the identifier of the event to subscribe to
     * @return if the operation succeded or not
     */
    public boolean subscribeOnEvent(User user, int eventId) {
        return false;
    }

    /**
     * Unsubscribes a user on an event
     *
     * @param user the user who wants to unsubscribe
     * @param eventId the identifier of the event to unsubscribe to
     * @return if the operation succeded or not
     */
    public boolean unsubscribeOnEvent(User user, int eventId) {
        return false;
    }

}
