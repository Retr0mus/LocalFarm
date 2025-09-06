package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.dtos.event.EventDTO;
import com.github.countrybros.model.event.Event;

import java.util.ArrayList;
import java.util.List;


public class EventMapper {

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
