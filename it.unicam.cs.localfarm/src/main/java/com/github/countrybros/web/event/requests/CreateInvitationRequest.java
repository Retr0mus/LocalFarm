package com.github.countrybros.web.event.requests;

import com.github.countrybros.model.event.Event;

import java.time.LocalDate;

/**
 * DTO for request to create an invitation
 */
public class CreateInvitationRequest {

    public Event event;

    public LocalDate expiration;

    public int receiverId;
}
