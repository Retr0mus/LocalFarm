package com.github.countrybros.application.event;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.web.event.requests.CreateEventRequest;
import com.github.countrybros.web.event.requests.EditEventRequest;
import com.github.countrybros.web.event.requests.EventElement;

import java.util.List;

/**
 * Interface that represents every possible implementation of EventService
 */
public interface IEventService {

    /**
     * Creates an event, assigns it to the organizer, sets initial status,
     * and delegates sending invitations.
     *
     * @param request the request of the event to create.
     */
    public void createEvent(CreateEventRequest request);

    /**
     * Removes an event from the repository.
     *
     * @param eventId the identifier of the event to remove.
     *
     * @throws NotFoundInRepositoryException if the event searched doesn't exist
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
     * Modifies the event specified, if present.
     *
     * @param request The request to modify an event.
     *
     * @throws NotFoundInRepositoryException if the event was not in the repo.
     */
    public void editEvent(EditEventRequest request);

    /**
     * Returns a list of all public events.
     *
     * @return list of events with status PUBLIC.
     */
    public List<EventElement> getPublicEvents();

    /**
     * Returns all the events in the website.
     *
     * @return a list with all the events.
     */
    public List<EventElement> getAllEvents();

    /**
     * Subscribes a user on an event.
     *
     * @param userId the user ID who wants to subscribe.
     * @param eventId the identifier of the event to subscribe to.
     *
     * @throws RequestAlreadySatisfiedException if already subscribed.
     */
    public void subscribeOnEvent(int userId, int eventId);

    /**
     * Unsubscribes a user on an event.
     *
     * @param userId the user ID who wants to unsubscribe.
     * @param eventId the identifier of the event to unsubscribe to.
     *
     * @throws RequestAlreadySatisfiedException if not subscribed
     */
    public void unsubscribeOnEvent(int userId, int eventId);

    /**
     * Cancels an event by setting its status as CANCELED.
     *
     * @param eventId the ID of the event to cancel.
     *
     * @throws RequestAlreadySatisfiedException if the event is already canceled
     */
    public void setAsCanceled(int eventId);

    /**
     * Confirms the publication of an event by changing its status to PUBLIC.
     *
     * @param eventId the ID of the event to publish.
     *
     * @throws RequestAlreadySatisfiedException if the event is already public.
     */
    public void confirmEventPublication(int eventId);

    /**
     * The invitation of a company on an event will be refused/deleted;
     *
     * @param companyId the company ID that signs out.
     * @param eventId the event.
     */
    public void cancelCompanyParticipation(int companyId, int eventId);

    /**
     * Confirms the participation of a certain company to an event.
     *
     * @param eventId the event to participate to.
     * @param companyId the company who decided to participate
     *
     * @throws RuntimeException if the company was already included among the event's guests
     */
    public void confirmCompanyParticipation(int eventId, int companyId);

}
