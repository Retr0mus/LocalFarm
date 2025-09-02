package com.github.countrybros.application.models.requests.event;

import com.github.countrybros.model.event.Location;
import com.github.countrybros.model.event.TimeInterval;
import java.util.List;

/**
 * DTO for requesting the creation of an event
 */
public class CreateEventRequest {

    public String name;

    public int maxSpots;

    public List<Integer> guestsId;

    public Location location;

    public List<TimeInterval> dates;

    public int organizerId;
}
