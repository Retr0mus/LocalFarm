package com.github.countrybros.application;

import com.github.countrybros.infrastructure.IEventRepository;
import com.github.countrybros.infrastructure.local.LocalEventRepository;
import com.github.countrybros.model.Company;
import com.github.countrybros.model.Event;
import com.github.countrybros.model.EventState;
import com.github.countrybros.model.User;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;

import java.util.List;


/**
 * Service that performs all the tasks related to the management of the events.
 *
 * TODO: remember to use only IDs as parameters (when possible of course)
 * TODO: remember to remove the Singleton pattern when porting to SpringBoot
 */
public class EventService implements IEventService {

    private static final EventService instance = new EventService(new LocalEventRepository());
    private final IEventRepository eventRepository;

    public EventService(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public static EventService getInstance() {return instance;}

    /**
     * Returns all the events in the website.
     *
     * @return a list with all the events.
     */
    public List<Event> getEvents() {
        return null;
    }

    /**
     * Removes an event from the repository.
     *
     * @param eventId the identifier of the event to remove.
     * @return if the event was removed or not.
     */
    public boolean removeEvent(int eventId) {
        return eventRepository.removeEvent(eventId);
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
        return eventRepository.getPublicEvents();
    }

    /**
     * Cancels an event by setting its status as CANCELED.
     *
     * @param eventId the ID of the event to cancel.
     * @return true if the event was successfully canceled, false otherwise.
     */
    public boolean cancelEvent(int eventId) {
        //TODO: We should make this work with the editEvent method, and maybe change the
        // name to setEventAsCanceled due to ambiguous interpretations
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
        //TODO: We should make this work with the addEvent method
        try {
            eventRepository.getEventById(eventDetails.getId());
        }catch (NotFoundInRepositoryException e) { return false; }

        eventDetails.setState(EventState.planning);
        eventRepository.addEvent(eventDetails);

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
        //TODO: We should make this work with the editEvent method
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
        return eventRepository.getEventById(eventId);
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
        //TODO: We should make this work with the editEvent method
        Event event = getEventById(eventId);
        List<Company> guests = event.getGuests();

        if (!guests.contains(company)) {

            throw new RuntimeException("Company not included in event guest list");
        }

        guests.remove(company);
        return true;
    }

    /**
     * Confirms the participation of a certain company to an event.
     *
     * @param eventId the event to participate to.
     * @param companyId the company who decided to participate
     * @return if the company was successfully added as a guest.
     */
    public boolean confirmCompanyPartecipation(int eventId, int companyId) {
        //TODO: Finish implementation when the CompanyManager is in this branch.
        // Also, the same thing said about the editEvent applies here.

        Event event = getEventById(eventId);
        List<Company> guests = event.getGuests();

        //if (!guests.contains(company)) {}

        return true;
    }
}
