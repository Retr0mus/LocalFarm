package com.github.countrybros.application.models.dtos.event;

import com.github.countrybros.model.event.Location;
import com.github.countrybros.model.event.TimeInterval;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents alla the information that the client needs to display it at users.
 */
public class InvitationDto {

    public LocalDate expiration;
    public String eventName;
    public String eventDescription;
    public List<TimeInterval> timeIntervals;
    public Location location;

}
