package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.dtos.event.EventDto;
import com.github.countrybros.application.models.requests.event.CreateEventRequest;
import com.github.countrybros.model.event.Event;

public class EventMapper {

    public static Event toDomain(CreateEventRequest request) {
        Event event = new Event();

        // TODO: Complete

        return event;
    }

    public static EventDto toDto(Event event) {
        EventDto eventDto = new EventDto();

        // TODO: Complete

        return eventDto;
    }


}
