package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.dtos.event.EventDTO;
import com.github.countrybros.model.event.Event;


public class EventMapper {

    public static EventDTO toDTO(Event event) {
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


}
