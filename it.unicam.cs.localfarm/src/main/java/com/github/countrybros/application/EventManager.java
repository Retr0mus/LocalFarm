package com.github.countrybros.application;

import com.github.countrybros.model.Company;
import com.github.countrybros.model.Event;
import com.github.countrybros.model.User;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.github.countrybros.model.EventState;


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

    /**
     * Returns a list of all public events.
     *
     * @return list of events with status PUBLIC.
     */
    public List<Event> getPublicEvents() {

        List<Event> publicEvents = new ArrayList<>();
        for (Event event : eventRepository.values()) {
            if (event.getState() == EventState.currentlyPublic) {
                publicEvents.add(event);
            }
        }
        return publicEvents;
    }

    /**
     * Cancels an event by setting its status to CANCELED.
     *
     * @param eventId the ID of the event to cancel.
     * @return true if the event was successfully canceled, false otherwise.
     */
    public boolean cancelEvent(int eventId) {
        Event event = getEventById(eventId);
        if (event == null || event.getState() == EventState.canceled || event.getState() == EventState.completed) {
            return false;
        }
        event.setState(EventState.canceled);
        return true;
    }

    /**
     * Creates an event, assigns it to the organizer, sets initial status,
     * and delegates sending invitations.
     *
     * @param eventDetails the event to create.
     * @return true if event was created successfully, false otherwise.
     */
    public boolean createEvent(Event eventDetails, List<Company> companiesToInvite) {
        if (eventRepository.containsKey(eventDetails.getId())) {
            return false;
        }

        eventDetails.setState(EventState.currentlyPublic);
        eventRepository.put(eventDetails.getId(), eventDetails);

        //TODO invitation part

        return true;
    }

    /**
     * Confirms the publication of an event by changing its status to PUBLIC.
     *
     * @param eventId the ID of the event to publish.
     * @return true if successfully published, false otherwise.
     */
    public boolean confirmEventPublication(int eventId) {
        Event event = getEventById(eventId);
        if (event == null ) {
            return false;
        }
        event.setState(EventState.currentlyPublic);
        return true;
    }

}
