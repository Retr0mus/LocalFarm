package com.github.countrybros.application;

<<<<<<< Updated upstream
=======
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
>>>>>>> Stashed changes
import com.github.countrybros.model.Company;
import com.github.countrybros.model.Event;
import com.github.countrybros.model.EventState;
import com.github.countrybros.model.User;


import java.util.ArrayList;
<<<<<<< Updated upstream
import java.util.HashMap;
=======
import java.util.HashSet;
>>>>>>> Stashed changes
import java.util.List;
import java.util.Map;
import com.github.countrybros.model.EventState;


/**
<<<<<<< Updated upstream
 * Service that performs all the tasks related to the management of the events.
=======
 * Service that performs all the tasks releted to the managemnent of the events.
 *
 * TODO cercare un modo per usare solo gli ID come parametri
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
     * Returns the event specified in the request.
=======
     * Returns all the public events in the website.
     *
     * @return a list with the public events.
     */
    public List<Event> getPublicEvents() {

        List<Event> list = new ArrayList<>(eventRepository.values());

        for (Event event : eventRepository.values()) {

            if (event.getState().equals(EventState.currentlyPublic))
                list.add(event);
        }

        return list;
    }

    /**
     * Adds an event into the repository
>>>>>>> Stashed changes
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

    /**
     * Returns the event associated with the specified ID.
     *
     * @param eventId ID of the event.
     * @return the event requested.
     *
     * @throws NotFoundInRepositoryException if the event is not found.
     */
    public Event getEventById(int eventId) {

        if (!eventRepository.containsKey(eventId)) {
            throw new NotFoundInRepositoryException("Event not found");
        }

        return eventRepository.get(eventId);
    }

    /**
     * Cancels the participation of a company on an event in which is already joined in.
     *
     * @param company the company that signs out.
     * @param eventId the event.
     *
     * @throws RuntimeException if the company is not included among the event's guests
     */
    public boolean cancelCompanyParticipation(Company company, int eventId) {

        Event event = getEventById(eventId);
        List<Company> guests = event.getGuests();

        if (!guests.contains(company)) {

            throw new RuntimeException("Company not included in event guest list");
        }

        guests.remove(company);
        return true;
    }
}
