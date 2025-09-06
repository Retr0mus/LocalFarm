package com.github.countrybros.application.services.event;

import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.application.services.company.ICompanyService;
import com.github.countrybros.application.services.user.UserService;
import com.github.countrybros.infrastructure.repositories.event.IEventRepository;
import com.github.countrybros.model.event.Invitation;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.model.event.EventState;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.model.user.User;
import com.github.countrybros.application.models.requests.event.CreateEventRequest;
import com.github.countrybros.application.models.requests.event.CreateInvitationRequest;
import com.github.countrybros.application.models.requests.event.EditEventRequest;
import com.github.countrybros.application.models.requests.event.EventElement;
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

    private final IEventRepository IEventRepository;
    private final ICompanyService companyService;
    private final UserService userService;
    private final IInvitationService invitationService;

    public EventService(IEventRepository IEventRepository, ICompanyService companyService,
                        UserService userService, IInvitationService invitationService) {

        this.IEventRepository = IEventRepository;
        this.companyService = companyService;
        this.userService = userService;
        this.invitationService = invitationService;
    }

    @Override
    public List<Event> getAllEvents() {

        return StreamSupport.stream(IEventRepository.findAll().spliterator(), false)
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

        this.IEventRepository.save(event);
    }

    @Override
    public void subscribeOnEvent(int userId, int eventId) {

        Event event = getEvent(eventId);
        User user = userService.getUser(userId);

        if (event.getSubscribers().contains(user))
            throw new RequestAlreadySatisfiedException("User already subscribed");

        event.subscribe(user);
        IEventRepository.save(event);
    }

    @Override
    public void unsubscribeOnEvent(int userId, int eventId) {

        Event event = getEvent(eventId);
        User user = userService.getUser(userId);

        if (!event.getSubscribers().contains(user))
            throw new RequestAlreadySatisfiedException("User was unsubscribed");

        event.unsubscribe(user);
        IEventRepository.save(event);
    }

    @Override
    public List<Event> getPublicEvents() {

        return IEventRepository.getAllByState(EventState.currentlyPublic);
    }

    @Override
    public void setAsCanceled(int eventId) {

        Event event = getEvent(eventId);

        if (event.getState() == EventState.canceled)
            throw new RequestAlreadySatisfiedException("Event already canceled");

        if (event.getState().equals(EventState.completed))
            throw new ImpossibleRequestException("Event already completed");

        event.setState(EventState.canceled);
        this.IEventRepository.save(event);
    }

    @Override
    public void createEvent(CreateEventRequest request) {

        Company organizer = companyService.getCompany(request.organizerId);

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
        IEventRepository.save(event);
    }

    @Override
    public Event getEvent(int eventId) {

        Event event = this.IEventRepository.findById(eventId).orElse(null);

        if (event == null)
            throw new NotFoundInRepositoryException("Event not found");

        return event;
    }

    @Override
    public void cancelCompanyParticipation(Company company, Event event) {

        if (event.getState().equals(EventState.completed) || event.getState().equals(EventState.canceled))
            throw new ImpossibleRequestException("Incoherent event status");

        if (! event.getGuests().contains(company))
            throw new RequestAlreadySatisfiedException("Company is not participating");

        event.getGuests().remove(company);
        IEventRepository.save(event);
    }

    @Override
    public void confirmCompanyParticipation(Event event, Company company) {

        if (event.getGuests().contains(company))
            throw new RequestAlreadySatisfiedException("Company already included in event guest list");

        event.getGuests().add(company);
        IEventRepository.save(event);
    }

    @Override
    public List<Event> getParticipations(Company company) {

        List<Company> companies = new ArrayList<>();
        companies.add(company);
        return IEventRepository.getAllByParticipantsIsContaining(companies);
    }

}
