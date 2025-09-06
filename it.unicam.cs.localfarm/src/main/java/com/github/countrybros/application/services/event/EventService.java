package com.github.countrybros.application.services.event;

import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.application.mappers.EventMapper;
import com.github.countrybros.infrastructure.repositories.event.IEventRepository;
import com.github.countrybros.model.event.*;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.model.user.User;
import com.github.countrybros.application.models.requests.event.CreateEventRequest;
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

    private final IEventRepository eventRepository;

    private final IInvitationService invitationService;

    public EventService(IEventRepository eventRepository,
                         IInvitationService invitationService) {

        this.eventRepository = eventRepository;

        this.invitationService = invitationService;
    }

    @Override
    public List<Event> getAllEvents() {

        return StreamSupport.stream(eventRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }



    public Event getLastCreatedEvent(){
        return eventRepository.findTopByOrderByIdDesc();
    }

    @Override
    public void createEvent(CreateEventRequest request, User organizer) {
        Event event = EventMapper.toDomain(request, organizer);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(int eventId, int organizerId) {

        Event event = getEvent(eventId);

        if (event.getOrganizer().getId() != organizerId) {
            throw new ImpossibleRequestException("User is not the organizer of this event");
        }

        if(event.getState() == EventState.completed) {
            throw new ImpossibleRequestException("The event with id: "+ event.getId() +" is over, so it cannot be canceled.");
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

        return eventRepository.getAllByState(EventState.currentlyPublic);
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
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundInRepositoryException("Event not found"));
    }

    @Override
    public void cancelCompanyParticipation(Company company, Event event) {

        if (event.getState().equals(EventState.completed))
            throw new ImpossibleRequestException("Incoherent event status");

        if (! event.getGuests().contains(company))
            throw new RequestAlreadySatisfiedException("Company is not participating");

        event.getGuests().remove(company);
        eventRepository.save(event);
    }

    @Override
    public void confirmCompanyParticipation(Event event, Company company) {

        if (event.getGuests().contains(company))
            throw new RequestAlreadySatisfiedException("Company already included in event guest list");

        event.getGuests().add(company);
        eventRepository.save(event);
    }

    @Override
    public List<Event> getParticipations(Company company) {

        List<Company> companies = new ArrayList<>();
        companies.add(company);
        return eventRepository.getAllByParticipantsIsContaining(companies);
    }

    public boolean existsByName(String name) {
        return eventRepository.existsByName(name);
    }

    @Override
    public List<Event> getEventsSubscribedByUser(int userId) {
        return eventRepository.findAllBySubscribers_Id(userId);
    }

    @Override
    public List<Event> getEventsByOrganizer(User organizer) {
        return eventRepository.findAllByOrganizer(organizer);
    }

    @Override
    public List<Event> getEventsByDate(LocalDate localDate) {
        List<Event> allEvents = StreamSupport.stream(eventRepository.findAll().spliterator(), false)
                .toList();

        return allEvents.stream()
                .filter(event -> event.getDates().stream()
                        .anyMatch(interval -> interval.getStartTime().toLocalDate().equals(localDate)))
                .collect(Collectors.toList());
    }
}
