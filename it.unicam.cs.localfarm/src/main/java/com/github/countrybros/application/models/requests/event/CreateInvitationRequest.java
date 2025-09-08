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

    public CreateInvitationRequest() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }
}
