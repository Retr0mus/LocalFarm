package com.github.countrybros.application.models.requests.event;

import com.github.countrybros.model.utils.Location;
import com.github.countrybros.model.event.TimeInterval;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * DTO for requesting the creation of an event
 */
public class CreateEventRequest {

    @NotBlank(message = "Event name is required")
    public String name;

    @Min(value = 1, message = "Max spots must be at least 1")
    public int maxSpots;

    @NotEmpty(message = "At least one guest must be invited")
    public List<Integer> guestsId;

    @NotNull(message = "Location is required")
    @Valid
    public Location location;

    @NotEmpty(message = "At least one date interval must be provided")
    @Valid
    public List<TimeInterval> dates;

    @NotNull(message = "Organizer ID is required")
    public Integer organizerId;

    @NotBlank(message = "Event description is required")
    public String description;
}
