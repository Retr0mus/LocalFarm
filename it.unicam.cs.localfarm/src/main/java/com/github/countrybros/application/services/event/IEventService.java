package com.github.countrybros.application.services.event;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.application.models.requests.event.CreateEventRequest;
import com.github.countrybros.application.models.requests.event.EditEventRequest;
import com.github.countrybros.application.models.requests.event.EventElement;

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
    void createEvent(CreateEventRequest request);

    /**
     * Returns the event associated with the specified ID.
     *
     * @param eventId ID of the event.
     * @return the event requested.
     *
     * @throws NotFoundInRepositoryException if the event is not found.
     */
    Event getEvent(int eventId);

    /**
     * Modifies the event specified, if present.
     *
     * @param request The request to modify an event.
     *
     * @throws NotFoundInRepositoryException if the event was not in the repo.
     */
    void editEvent(EditEventRequest request);

    /**
     * Returns a list of all public events.
     *
     * @return list of events with status PUBLIC.
     */
    List<Event> getPublicEvents();

    /**
     * Returns all the events in the website.
     *
     * @return a list with all the events.
     */
    List<Event> getAllEvents();

    /**
     * Subscribes a user on an event.
     *
     * @param userId the user ID who wants to subscribe.
     * @param eventId the identifier of the event to subscribe to.
     *
     * @throws RequestAlreadySatisfiedException if already subscribed.
     */
    void subscribeOnEvent(int userId, int eventId);

    /**
     * Unsubscribes a user on an event.
     *
     * @param userId the user ID who wants to unsubscribe.
     * @param eventId the identifier of the event to unsubscribe to.
     *
     * @throws RequestAlreadySatisfiedException if not subscribed
     */
    void unsubscribeOnEvent(int userId, int eventId);

    /**
     * Cancels an event by setting its status as CANCELED.
     *
     * @param eventId the ID of the event to cancel.
     *
     * @throws RequestAlreadySatisfiedException if the event is already canceled
     */
    void setAsCanceled(int eventId);

    /**
     * Confirms the publication of an event by changing its status to PUBLIC.
     *
     * @param eventId the ID of the event to publish.
     *
     * @throws RequestAlreadySatisfiedException if the event is already public.

     */
    void confirmEventPublication(int eventId);

    /**
     * The invitation of a company on an event will be refused/deleted;
     * If it's not participating, or the event is canceled or has ended, throws errors.
     *
     * @param company the company that signs out.
     * @param event the event.
     */
    void cancelCompanyParticipation(Company company, Event event);

    /**
     * Confirms the participation of a certain company to an event.
     *
     * @param event the event to participate to.
     * @param company the company who decided to participate
     *
     * @throws RuntimeException if the company was already included among the event's guests
     */
    void confirmCompanyParticipation(Event event, Company company);

    /**
     * Returns all the participation of a company in a planning or public event.
     *
     * @param company id of the company.
     *
     * @return a list of all events in witch is participating.
     */
    List<Event> getParticipations(Company company);

}
