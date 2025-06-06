package com.github.countrybros.web.event;

import com.github.countrybros.model.event.Event;
import com.github.countrybros.model.user.Company;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

/**
 * DTO for the request of creating a new event
 */
public class CreateEventRequest {

    @NotNull
    public Event event;

    @NotNull
    public ArrayList<Company> invitedCompanies;
}
