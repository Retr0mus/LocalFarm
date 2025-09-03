package com.github.countrybros.application.services.event;

import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.application.mappers.EventMapper;
import com.github.countrybros.application.services.company.ICompanyService;
import com.github.countrybros.application.services.user.UserService;
import com.github.countrybros.infrastructure.repositories.event.IEventRepository;
import com.github.countrybros.model.event.*;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.model.user.User;
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
    private final ICompanyService companyService;
    private final UserService userService;
    private final IInvitationService invitationService;

    public EventService(IEventRepository eventRepository, ICompanyService companyService,
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



    public Event getLastCreatedEvent(){
        return eventRepository.findTopByOrderByIdDesc();
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
    public void createEvent(CreateEventRequest request, Company organizer) {

        Location location = request.location;
        List<TimeInterval> dates = request.dates;

        Event event = new Event(request.name, request.maxSpots);
        event.setLocation(location);
        event.setDates(dates);
        event.setOrganizer(organizer);
        event.setState(EventState.planning);

        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(int eventId, int organizerId) {

        Event event = getEvent(eventId);

        if (event.getOrganizer().getId() != organizerId) {
            throw new ImpossibleRequestException("User is not the organizer of this event");
        }

        eventRepository.delete(event);
    }

    @Override
    public void subscribeToEvent(User user, int eventId) {

        Event event = getEvent(eventId);
        if (event.isFull()) {
            throw new ImpossibleRequestException("No more spots available for this event");
        }

        event.subscribe(user);
        eventRepository.save(event);
    }

    @Override
    public void unSubscribeFromEvent(User user, int eventId) {
        Event event = getEvent(eventId);
        event.unsubscribe(user);
        eventRepository.save(event);
    }

    @Override
    public List<Event> getPublicEvents() {
        return eventRepository.findAllByState(EventState.currentlyPublic);
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
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundInRepositoryException("Event not found"));
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

    public boolean existsByName(String name) {
        return eventRepository.existsByName(name);
    }

    @Override
    public List<Event> getEventsSubscribedByUser(int userId) {
        return eventRepository.findAllBySubscribers_Id(userId);
    }

    @Override
    public List<Event> getEventsByOrganizer(Company organizer) {
        return eventRepository.findAllByOrganizer(organizer);
    }

}
