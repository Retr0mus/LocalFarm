package com.github.countrybros.application.services.event;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.application.models.requests.event.CreateEventRequest;
import com.github.countrybros.application.models.requests.event.EditEventRequest;
import com.github.countrybros.application.models.requests.event.EventElement;
import com.github.countrybros.model.user.User;

import java.time.LocalDate;
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
    void createEvent(CreateEventRequest request, User organizer);

    /**
     * Removes an event from the repository.
     *
     * @param eventId the identifier of the event to remove.
     *
     * @throws NotFoundInRepositoryException if the event searched doesn't exist
     */
    void deleteEvent(int eventId, int organizerId);

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
    List<EventElement> getAllEvents();

    /**
     * Subscribes a user on an event.
     *
     * @param user the user who wants to subscribe.
     * @param eventId the identifier of the event to subscribe to.
     *
     * @throws RequestAlreadySatisfiedException if already subscribed.
     */
    void subscribeToEvent(User user, int eventId);

    /**
     * Unsubscribes a user on an event.
     *
     * @param user the user ID who wants to unsubscribe.
     * @param eventId the identifier of the event to unsubscribe to.
     *
     * @throws RequestAlreadySatisfiedException if not subscribed
     */
    void unSubscribeFromEvent(User user, int eventId);

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
     *
     * @param companyId the company ID that signs out.
     * @param eventId the event.
     */
    void cancelCompanyParticipation(int companyId, int eventId);

    /**
     * Confirms the participation of a certain company to an event.
     *
     * @param eventId the event to participate to.
     * @param companyId the company who decided to participate
     *
     * @throws RuntimeException if the company was already included among the event's guests
     */
    void confirmCompanyParticipation(int eventId, int companyId);

    /**
     *
     * Get the last event created
     *
     * @return
     */
    Event getLastCreatedEvent();

    boolean existsByName(String name);

    List<Event> getEventsSubscribedByUser(int userId);

    List<Event> getEventsByOrganizer(User organizer);

    List<Event> getEventsByDate(LocalDate localDate);
}
