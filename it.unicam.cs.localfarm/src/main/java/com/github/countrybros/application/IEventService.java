package com.github.countrybros.application;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.model.Company;
import com.github.countrybros.model.Event;
import com.github.countrybros.model.User;

import java.util.List;

/**
 * Interface that represents every possible implementation of EventService
 */
public interface IEventService {

    /**
     * Creates an event, assigns it to the organizer, sets initial status,
     * and delegates sending invitations.
     *
     * @param eventDetails the event to create.
     * @return true if event was created successfully, false otherwise.
     */
    public boolean createEvent(Event eventDetails, List<Company> companiesToInvite);

    /**
     * Removes an event from the repository.
     *
     * @param eventId the identifier of the event to remove.
     * @return if the event was removed or not.
     */
    public void removeEvent(int eventId);

    /**
     * Returns the event associated with the specified ID.
     *
     * @param eventId ID of the event.
     * @return the event requested.
     *
     * @throws NotFoundInRepositoryException if the event is not found.
     */
    public Event getEvent(int eventId);

    /**
     * Returns a list of all public events.
     *
     * @return list of events with status PUBLIC.
     */
    public List<Event> getPublicEvents();

    /**
     * Returns all the events in the website.
     *
     * @return a list with all the events.
     */
    public List<Event> getEvents();

    /**
     * Subscribes a user on an event.
     *
     * @param userId the user ID who wants to subscribe.
     * @param eventId the identifier of the event to subscribe to.
     * @return if the operation succeeded or not.
     */
    public boolean subscribeOnEvent(int userId, int eventId);

    /**
     * Unsubscribes a user on an event.
     *
     * @param userId the user ID who wants to unsubscribe.
     * @param eventId the identifier of the event to unsubscribe to.
     * @return if the operation succeeded or not.
     */
    public boolean unsubscribeOnEvent(int userId, int eventId);

    /**
     * Cancels an event by setting its status as CANCELED.
     *
     * @param eventId the ID of the event to cancel.
     * @return true if the event was successfully canceled, false otherwise.
     */
    public boolean cancelEvent(int eventId);

    /**
     * Confirms the publication of an event by changing its status to PUBLIC.
     *
     * @param eventId the ID of the event to publish.
     * @return true if successfully published, false otherwise.
     */
    public boolean confirmEventPublication(int eventId);

    /**
     * Cancels the participation of a company on an event in which is already joined in.
     *
     * @param companyId the company ID that signs out.
     * @param eventId the event.
     *
     * @throws RuntimeException if the company is not included among the event's guests
     */
    public boolean cancelCompanyParticipation(int companyId, int eventId);

    /**
     * Confirms the participation of a certain company to an event.
     *
     * @param eventId the event to participate to.
     * @param companyId the company who decided to participate
     * @return if the company was successfully added as a guest.
     */
    public boolean confirmCompanyPartecipation(int eventId, int companyId);

}
