package com.github.countrybros.model;

import com.github.countrybros.application.InvitationManager;

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
    public boolean onApproval() {

        if (expirationDate.isAfter(LocalDate.now())) {
            event.addGuest(reciver);
            return true;
        }

        return false;
    }

    /**
     * Deletes the invitation from the Manager without doing other tasks
     */
    @Override
    public boolean onRevocation() {
        return true;
    }

    /**
     * Prints all the details about the event.
     */
    public void getDetails() {
        //TODO: This method is pure shit, must change with a better pattern

        System.out.println("ID: " + id);
        System.out.println("Event: " + event);
        System.out.println("Expiration date: " + expirationDate);
        System.out.println("Reciver: " + reciver);
    }
}
