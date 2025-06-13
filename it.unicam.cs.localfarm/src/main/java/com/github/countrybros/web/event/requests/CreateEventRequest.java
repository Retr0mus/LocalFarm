package com.github.countrybros.web.event.requests;

import com.github.countrybros.model.event.EventState;
import com.github.countrybros.model.event.Location;
import com.github.countrybros.model.event.TimeInterval;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.model.user.User;
import jakarta.persistence.*;

import java.util.ArrayList;
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
