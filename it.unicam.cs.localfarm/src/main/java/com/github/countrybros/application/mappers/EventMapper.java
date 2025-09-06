package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.dtos.event.EventDTO;
import com.github.countrybros.application.models.dtos.event.EventDto;
import com.github.countrybros.application.models.requests.event.CreateEventRequest;
import com.github.countrybros.application.models.requests.event.CreateEventRequest;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.model.event.EventState;
import com.github.countrybros.model.user.User;
import com.github.countrybros.model.event.EventState;
import com.github.countrybros.model.user.User;

import java.util.ArrayList;
import java.util.List;


public class EventMapper {

    public static Event toDomain(CreateEventRequest request, User organizer) {
        Event event = new Event(request.name, request.maxSpots);
        event.setLocation(request.location);
        event.setDates(request.dates);
        event.setOrganizer(organizer);
        event.setState(EventState.planning);
        event.setDescription(request.description);
        return event;
    }

    public EventDTO toDTO(Event event) {
        return new EventDTO(
                event.getId(),
                event.getName(),
                event.getMaxSpots(),
                event.getLocation(),
                event.getDates(),
                CompanyMapper.toDTO(event.getOrganizer()),
                event.getSubscribers(),
                event.getGuests(),
                event.getState().name(),
                event.getDescription()
        );
    }

    public List<EventDTO> toDTO(List<Event> events) {

        List<EventDTO> dtos = new ArrayList<>();
        for (Event event : events) {
            dtos.add(toDTO(event));
        }

        return dtos;
    }
}
