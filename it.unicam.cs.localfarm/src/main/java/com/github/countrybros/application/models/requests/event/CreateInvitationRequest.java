package com.github.countrybros.application.models.requests.event;

import com.github.countrybros.model.event.Event;

import java.time.LocalDate;

/**
 * DTO for request to create an invitation
 */
public class CreateInvitationRequest {

    public int eventId;

    public LocalDate expiration;

    public int receiverId;

}
