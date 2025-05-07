package com.github.countrybros.model;

import java.time.LocalDate;

/**
 * Class that represents an invitation for a certain company to
 * participate as a guest into an event.
 */
public class Invitation implements IBinaryChoiceable {

    private int id;
    private final Event event;
    private final LocalDate expirationDate;
    private final Company reciver;

    public Invitation(Event event, LocalDate expirationDate, Company reciver) {
        this.event = event;
        this.expirationDate = expirationDate;
        this.reciver = reciver;
    }

    public Event getEvent() {
        return event;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public Company getReciver() {
        return reciver;
    }

    /**
     * Subscribes the reciver Company into the Event's guests
     */
    @Override
    public void onApproval() {

    }

    /**
     * Deletes the invitation from the Manager without doing other tasks
     */
    @Override
    public void onRevocation() {

    }
}
