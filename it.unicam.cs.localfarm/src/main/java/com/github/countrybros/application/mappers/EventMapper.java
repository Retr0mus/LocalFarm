package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.dtos.event.EventDto;
import com.github.countrybros.application.models.requests.event.CreateEventRequest;
import com.github.countrybros.application.services.company.ICompanyService;
import com.github.countrybros.application.services.user.IUserService;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.model.event.EventState;
import com.github.countrybros.model.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventMapper {

    private final ICompanyService companyService;
    private final IUserService userService;

    public EventMapper(ICompanyService companyService, IUserService userService) {
        this.companyService = companyService;
        this.userService = userService;
    }

    public Event toDomain(CreateEventRequest request) {
        User organizer = userService.getUser(request.organizerId);

        Event event = new Event(request.name, request.maxSpots);
        event.setLocation(request.location);
        event.setDates(request.dates);
        event.setOrganizer(organizer);
        event.setState(EventState.planning);
        event.setDescription(request.description);

        return event;
    }

    public static EventDto toDTO(Event event) {
        return new EventDto(
                event.getId(),
                event.getName(),
                event.getMaxSpots(),
                event.getLocation(),
                event.getDates(),
                UserMapper.toDto(event.getOrganizer()),
                event.getSubscribers(),
                event.getGuests(),
                event.getState().name(),
                event.getDescription()
        );
    }

    public List<EventDto> toDTO(List<Event> events) {

        List<EventDto> dtos = new ArrayList<>();
        for (Event event : events) {
            dtos.add(toDTO(event));
        }

        return dtos;
    }
}
