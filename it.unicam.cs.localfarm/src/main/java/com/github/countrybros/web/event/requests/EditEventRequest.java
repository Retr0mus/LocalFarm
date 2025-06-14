package com.github.countrybros.web.event.requests;

import com.github.countrybros.model.event.Location;
import com.github.countrybros.model.event.TimeInterval;

import java.util.List;

/**
 * DTO to request an updating an event.
 */
public class EditEventRequest {

    public int eventId;

    public String name;

    public int maxSpots;

    public Location location;

    public List<TimeInterval> dates;

    public int organizerId;
}
