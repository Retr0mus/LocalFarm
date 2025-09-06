package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.dtos.event.EventDto;
import com.github.countrybros.application.models.requests.event.CreateEventRequest;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.model.event.EventState;
import com.github.countrybros.model.user.User;


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

    public static EventDto toDTO(Event event) {
        return new EventDto(
                event.getId(),
                event.getName(),
                event.getMaxSpots(),
                event.getLocation(),
                event.getDates(),
                UserMapper.toDto(event.getOrganizer()),
                event.getSubscribers(),
                event.getParticipants(),
                event.getState().name(),
                event.getDescription()
        );
    }


}
