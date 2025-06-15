package com.github.countrybros.application.event;

import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.application.user.ICompanyService;
import com.github.countrybros.application.user.UserService;
import com.github.countrybros.infrastructure.web.EventRepository;
import com.github.countrybros.model.event.Invitation;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.model.event.EventState;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.model.user.User;
import com.github.countrybros.web.event.requests.CreateEventRequest;
import com.github.countrybros.web.event.requests.CreateInvitationRequest;
import com.github.countrybros.web.event.requests.EditEventRequest;
import com.github.countrybros.web.event.requests.EventElement;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service that performs all the tasks related to the management of the events.
 *
 */
@Service
public class EventService implements IEventService {

    private final EventRepository eventRepository;
    private final ICompanyService companyService;
    private final UserService userService;
    private final IInvitationService invitationService;

    public EventService(EventRepository eventRepository, ICompanyService companyService,
                        UserService userService, IInvitationService invitationService) {

        this.eventRepository = eventRepository;
        this.companyService = companyService;
        this.userService = userService;
        this.invitationService = invitationService;
    }

    @Override
    public List<EventElement> getAllEvents() {

        return StreamSupport.stream(eventRepository.findAll().spliterator(), false)
                .map(event -> {
                    EventElement dto = new EventElement();
                    dto.id = event.getId();
                    dto.name = event.getName();
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void editEvent(EditEventRequest request) {

        Event event = getEvent(request.eventId);
        Company company = companyService.getCompany(request.organizerId);

        event.setLocation(request.location);
        event.setName(request.name);
        event.setDates(request.dates);
        event.setMaxSpots(request.maxSpots);
        event.setOrganizer(company);

        this.eventRepository.save(event);
    }

    @Override
    public void deleteEvent(int eventId) {

        Event event = this.eventRepository.findById(eventId).orElse(null);

        if (event == null)
            throw new NotFoundInRepositoryException("Event not found");

        eventRepository.delete(event);
    }

    @Override
    public void subscribeOnEvent(int userId, int eventId) {

        Event event = getEvent(eventId);
        User user = userService.getUser(userId);

        if (event.getSubscribers().contains(user))
            throw new RequestAlreadySatisfiedException("User already subscribed");

        event.subscribe(user);
        eventRepository.save(event);
    }

    @Override
    public void unsubscribeOnEvent(int userId, int eventId) {

        Event event = getEvent(eventId);
        User user = userService.getUser(userId);

        if (!event.getSubscribers().contains(user))
            throw new RequestAlreadySatisfiedException("User was unsubscribed");

        event.unsubscribe(user);
        eventRepository.save(event);
    }

    @Override
    public List<EventElement> getPublicEvents() {

        return eventRepository.getAllByState(EventState.currentlyPublic)
                        .stream()
                .map(event -> {
                    EventElement dto = new EventElement();
                    dto.id = event.getId();
                    dto.name = event.getName();
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void setAsCanceled(int eventId) {

        Event event = getEvent(eventId);

        if (event.getState() == EventState.canceled)
            throw new RequestAlreadySatisfiedException("Event already canceled");

        if (event.getState().equals(EventState.completed))
            throw new ImpossibleRequestException("Event already completed");

        event.setState(EventState.canceled);
        this.eventRepository.save(event);
    }

    @Override
    public void createEvent(CreateEventRequest request) {

        Company organizer = companyService.getCompany(request.organizerId);

        Event event = new Event(request.name, request.maxSpots);
        event.setDates(request.dates);
        event.setLocation(request.location);
        event.setOrganizer(organizer);

        eventRepository.save(event);

        //create and retrieve invitations
        for (Integer companyId : request.guestsId) {

            CreateInvitationRequest invitationRequest = new CreateInvitationRequest();
            invitationRequest.event = event;
            invitationRequest.expiration = LocalDate.now().plusDays(7);
            invitationRequest.receiverId = companyId;
            invitationService.addInvitation(invitationRequest);
        }
    }

    @Override
    public void confirmEventPublication(int eventId) {

        Event event = getEvent(eventId);

        if (event.getState().equals(EventState.currentlyPublic))
            throw new RequestAlreadySatisfiedException("Event is already public");

        if (event.getState().equals(EventState.completed))
            throw new ImpossibleRequestException("Event is completed");

        if (event.getState().equals(EventState.canceled))
            throw new RequestAlreadySatisfiedException("Event is canceled");

        event.setState(EventState.currentlyPublic);
        eventRepository.save(event);
    }

    @Override
    public Event getEvent(int eventId) {

        Event event = this.eventRepository.findById(eventId).orElse(null);

        if (event == null)
            throw new NotFoundInRepositoryException("Event not found");

        return event;
    }

    @Override
    public void cancelCompanyParticipation(int companyId, int eventId) {

        Event event = getEvent(eventId);
        Company company = this.companyService.getCompany(companyId);

        Invitation invitation = event.getGuestInvitation(company);

        invitationService.deleteInvitation(invitation.getId());
    }

    @Override
    public void confirmCompanyParticipation(int eventId, int companyId) {

        Event event = getEvent(eventId);
        Company company = this.companyService.getCompany(companyId);

        if (event.getGuests().contains(company))
            throw new RequestAlreadySatisfiedException("Company already included in event guest list");

        invitationService.acceptInvitation(event.getGuestInvitation(company).getId());
    }
}
