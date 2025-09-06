package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.dtos.event.EventDTO;
import com.github.countrybros.application.models.requests.event.CreateEventRequest;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.model.event.EventState;


public class EventMapper {

    public static Event toDomain(CreateEventRequest request) {
        Event event = new Event();

        // TODO: Complete

        return event;
    }

    public static EventDTO toDTO(Event event) {
        return new EventDTO(
                event.getId(),
                event.getName(),
                event.getMaxSpots(),
                event.getLocation(),
                event.getDates(),
                event.getOrganizer(),
                event.getSubscribers(),
                event.getParticipants(),
                event.getState().name(),
                event.getDescription()
        );
    }


}
