package com.github.countrybros.application.services.event;

import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.infrastructure.repositories.event.IEventRepository;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.model.event.EventState;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.models.requests.event.CreateEventRequest;
import com.github.countrybros.application.models.requests.event.EditEventRequest;
import com.github.countrybros.application.models.requests.event.EventElement;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service that performs all the tasks related to the management of the events.
 *
 */
@Service
public class EventService implements IEventService {

    private final IEventRepository eventRepository;


    public EventService(IEventRepository eventRepository) {

        this.eventRepository = eventRepository;
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

        /*Event event = getEvent(request.eventId);
        Company company = companyService.getCompany(request.organizerId);

        event.setLocation(request.location);
        event.setName(request.name);
        event.setDates(request.dates);
        event.setMaxSpots(request.maxSpots);
        event.setOrganizer(company);

        this.IEventRepository.save(event);*/
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

        /*Event event = getEvent(eventId);
        User user = userService.getUser(userId);

        if (event.getSubscribers().contains(user))
            throw new RequestAlreadySatisfiedException("User already subscribed");

        event.subscribe(user);
        IEventRepository.save(event);*/
    }

    @Override
    public void unsubscribeOnEvent(int userId, int eventId) {

        /*Event event = getEvent(eventId);
        User user = userService.getUser(userId);

        if (!event.getSubscribers().contains(user))
            throw new RequestAlreadySatisfiedException("User was unsubscribed");

        event.unsubscribe(user);
        IEventRepository.save(event);*/
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

    /**
     * Returns a list of the organizer's unconfirmed events
     *
     * @param userId the organizer's ID.
     * @return A list of the events.
     */
    @Override
    public List<EventElement> getPendingEvents(int userId) {
        return eventRepository.getAllByState(EventState.planning)
                .stream().map(
                        event -> {
                            EventElement dto = new EventElement();
                            dto.id = event.getId();
                            dto.name = event.getName();
                            return dto;
                        }
                ).collect(Collectors.toList());
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

        /*Company organizer = companyService.getCompany(request.organizerId);

        Event event = new Event(request.name, request.maxSpots);
        event.setDates(request.dates);
        event.setLocation(request.location);
        event.setOrganizer(organizer);

        IEventRepository.save(event);

        //create and retrieve invitations
        for (Integer companyId : request.guestsId) {

            CreateInvitationRequest invitationRequest = new CreateInvitationRequest();
            invitationRequest.event = event;
            invitationRequest.expiration = LocalDate.now().plusDays(7);
            invitationRequest.receiverId = companyId;
            invitationService.addInvitation(invitationRequest);
        }*/
    }

    @Override
    public void confirmEventPublication(int eventId, int userId) {
        Event event = getEvent(eventId);

        if(event.getState() != EventState.planning)
            throw new ImpossibleRequestException("Invalid event state, only event in the planning state can be published");

        if(event.getOrganizer().getId() != userId)
            throw new ImpossibleRequestException("Invalid event organizer");

        event.setState(EventState.currentlyPublic);
        eventRepository.save(event);
    }

    @Override
    public Event getEvent(int eventId) {
        return this.eventRepository.findById(eventId).orElseThrow(() -> new NotFoundInRepositoryException("Event not found"));
    }

    @Override
    public void cancelCompanyParticipation(int companyId, int eventId) {
/*
        Event event = getEvent(eventId);
        Company company = this.companyService.getCompany(companyId);

        Invitation invitation = event.getGuestInvitation(company);

        invitationService.deleteInvitation(invitation.getId());*/
    }

    @Override
    public void confirmCompanyParticipation(int eventId, int companyId) {
/*
        Event event = getEvent(eventId);
        Company company = this.companyService.getCompany(companyId);

        if (event.getGuests().contains(company))
            throw new RequestAlreadySatisfiedException("Company already included in event guest list");

        invitationService.acceptInvitation(event.getGuestInvitation(company).getId());*/
    }
}
