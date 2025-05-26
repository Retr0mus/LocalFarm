package com.github.countrybros.application.event;

import com.github.countrybros.application.user.ICompanyService;
import com.github.countrybros.infrastructure.IEventRepository;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.model.event.EventState;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that performs all the tasks related to the management of the events.
 *
 * TODO: remember to use only IDs as parameters (when possible of course)
 * TODO: remember to remove the Singleton pattern when porting to SpringBoot
 */
public class EventService implements IEventService {

    private final IEventRepository eventRepository;
    private final ICompanyService companyService;

    public EventService(IEventRepository eventRepository, ICompanyService companyService) {

        this.eventRepository = eventRepository;
        this.companyService = companyService;
    }

    /**
     * Returns all the events in the website.
     *
     * @return a list with all the events.
     */
    public List<Event> getEvents() {

        return this.eventRepository.getAll();
    }

    public void editEvent(Event event) {

        if (!this.eventRepository.exists(event.getId()))
            throw new NotFoundInRepositoryException("Event not found");

        this.eventRepository.save(event);
    }

    /**
     * Removes an event from the repository.
     *
     * @param eventId the identifier of the event to remove.
     */
    public void removeEvent(int eventId) {
        eventRepository.delete(eventId);
    }

    /**
     * Subscribes a user on an event.
     *
     * @param userId the user ID who wants to subscribe.
     * @param eventId the identifier of the event to subscribe to.
     * @return if the operation succeeded or not.
     */
    public boolean subscribeOnEvent(int userId, int eventId) {

        return false;
    }

    /**
     * Unsubscribes a user on an event.
     *
     * @param userId the user who wants to unsubscribe.
     * @param eventId the identifier of the event to unsubscribe to.
     * @return if the operation succeeded or not.
     */
    public boolean unsubscribeOnEvent(int userId, int eventId) {
        return false;
    }

    /**
     * Returns a list of all public events.
     *
     * @return list of events with status PUBLIC.
     */
    public List<Event> getPublicEvents() {

        ArrayList<Event> events = new ArrayList<>(this.getEvents());

        events.removeIf(e -> !(e.getState().equals(EventState.currentlyPublic)));

        return events;
    }

    /**
     * Cancels an event by setting its status as CANCELED.
     *
     * @param eventId the ID of the event to cancel.
     * @return true if the event was successfully canceled, false otherwise.
     */
    public boolean cancelEvent(int eventId) {
        //TODO: change setEventAsCanceled due to ambiguous interpretations
        Event event = getEvent(eventId);
        if (event == null || event.getState() == EventState.canceled || event.getState() == EventState.completed) {
            return false;
        }

        event.setState(EventState.canceled);
        this.eventRepository.save(event);
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

        try {
            eventRepository.get(eventDetails.getId());
        }catch (NotFoundInRepositoryException e) { return false; }

        eventDetails.setState(EventState.planning);
        eventRepository.save(eventDetails);

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

        Event event = getEvent(eventId);

        event.setState(EventState.currentlyPublic);
        this.editEvent(event);
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
    public Event getEvent(int eventId) {

        return eventRepository.get(eventId);
    }

    /**
     * Cancels the participation of a company on an event in which is already joined in.
     *
     * @param companyId the company that signs out.
     * @param eventId the event.
     *
     * @throws RuntimeException if the company is not included among the event's guests
     */
    public boolean cancelCompanyParticipation(int companyId, int eventId) {

        Event event = getEvent(eventId);
        Company company = this.companyService.getCompany(companyId);

        if (!event.getGuests().contains(company)) {

            throw new RuntimeException("Company not included in event guest list");
        }

        event.getGuests().remove(company);
        this.companyService.editCompany(company);
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

        Event event = getEvent(eventId);
        Company company = this.companyService.getCompany(companyId);

        if(event.getGuests().contains(company))
            throw new RuntimeException("Company already included in event guest list");

        event.getGuests().add(company);
        this.eventRepository.save(event);
        return true;
    }
}
