package com.github.countrybros.application.event;

import com.github.countrybros.application.errors.FoundInRepositoryException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.application.user.ICompanyService;
import com.github.countrybros.application.user.UserManager;
import com.github.countrybros.infrastructure.IEventRepository;
import com.github.countrybros.infrastructure.web.repository.WebEventRepository;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.model.event.EventState;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.model.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that performs all the tasks related to the management of the events.
 *
 */
@Service
public class EventService implements IEventService {

    private final WebEventRepository eventRepository;
    private final ICompanyService companyService;
    private final UserManager userService;

    public EventService(WebEventRepository eventRepository, ICompanyService companyService, UserManager userService) {

        this.eventRepository = eventRepository;
        this.companyService = companyService;
        this.userService = userService;
    }

    /**
     * Returns all the events in the website.
     *
     * @return a list with all the events.
     */
    public List<Event> getEvents() {

        List<Event> events = new ArrayList<>();
        eventRepository.findAll().forEach(events::add);
        return events;
    }

    public void editEvent(Event event) {

        if (!this.eventRepository.existsById(event.getId()))
            throw new NotFoundInRepositoryException("Event not found");

        this.eventRepository.save(event);
    }

    /**
     * Removes an event from the repository.
     *
     * @param eventId the identifier of the event to remove.
     */
    public void removeEvent(int eventId) {

        Event event = this.eventRepository.findById(eventId).orElse(null);

        if (event == null)
            throw new NotFoundInRepositoryException("Event not found");

        eventRepository.delete(event);
    }

    /**
     * Subscribes a user on an event.
     *
     * @param userId the user ID who wants to subscribe.
     * @param eventId the identifier of the event to subscribe to.
     *
     */
    public void subscribeOnEvent(int userId, int eventId) {

        Event event = getEvent(eventId);
        User user = userService.getUser(userId);

        if (event.getSubscribers().contains(user))
            throw new RequestAlreadySatisfiedException("User already subscribed");

        event.subscribe(user);
    }

    /**
     * Unsubscribes a user on an event.
     *
     * @param userId the user who wants to unsubscribe.
     * @param eventId the identifier of the event to unsubscribe to.
     *
     * @throws RequestAlreadySatisfiedException if the user was not subscribed.
     */
    public void unsubscribeOnEvent(int userId, int eventId) {
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
     *
     * @throws RequestAlreadySatisfiedException if the event was already canceled.
     */
    public void setAsCanceled(int eventId) {

        Event event = getEvent(eventId);
        if (event == null || event.getState() == EventState.canceled || event.getState() == EventState.completed)
            throw new RequestAlreadySatisfiedException("Event already canceled");

        event.setState(EventState.canceled);
        this.eventRepository.save(event);
    }

    /**
     * Creates an event, assigns it to the organizer, sets initial status,
     * and delegates sending invitations.
     *
     * @param eventDetails the event to create.
     */
    public void createEvent(Event eventDetails, List<Company> companiesToInvite) {

        if (eventRepository.existsById(eventDetails.getId()))
            throw new FoundInRepositoryException("Event already exists.");

        eventDetails.setState(EventState.planning);
        eventRepository.save(eventDetails);

        //TODO invitation part


    }

    /**
     * Confirms the publication of an event by changing its status to PUBLIC.
     *
     * @param eventId the ID of the event to publish.
     */
    public void confirmEventPublication(int eventId) {

        Event event = getEvent(eventId);

        if (event.getState().equals(EventState.currentlyPublic))
            throw new RequestAlreadySatisfiedException("Event is already public");

        event.setState(EventState.currentlyPublic);
        this.editEvent(event);
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

        return eventRepository.getById(eventId);
    }

    /**
     * Cancels the participation of a company on an event in which is already joined in.
     *
     * @param companyId the company that signs out.
     * @param eventId the event.
     *
     * @throws RuntimeException if the company is not included among the event's guests
     */
    public void cancelCompanyParticipation(int companyId, int eventId) {

        Event event = getEvent(eventId);
        Company company = this.companyService.getCompany(companyId);

        if (!event.getGuests().contains(company)) {

            throw new RequestAlreadySatisfiedException("Participation not found");
        }

        event.getGuests().remove(company);
        this.companyService.editCompany(company);
    }

    /**
     * Confirms the participation of a certain company to an event.
     *
     * @param eventId the event to participate to.
     * @param companyId the company who decided to participate
     */
    public void confirmCompanyParticipation(int eventId, int companyId) {

        Event event = getEvent(eventId);
        Company company = this.companyService.getCompany(companyId);

        if(event.getGuests().contains(company))
            throw new RuntimeException("Company already included in event guest list");

        event.getGuests().add(company);
        this.eventRepository.save(event);
    }
}
