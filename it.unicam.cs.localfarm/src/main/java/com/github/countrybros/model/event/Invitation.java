package com.github.countrybros.model.event;

import com.github.countrybros.model.user.Company;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Class that represents an invitation for a certain company to
 * participate as a guest into an event.
 */
public class Invitation {

    private int id;
    private final Event event;
    private final LocalDate expirationDate;
    private final Company reciver;

    public Invitation(Event event, LocalDate expirationDate, Company reciver) {
        this.event = event;
        this.expirationDate = expirationDate;
        this.reciver = reciver;
    }

    public int getId() {return id;}

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
     * Prints all the details about the event.
     */
    public void getDetails() {
        //TODO: This method is pure shit, must change with a better pattern

        System.out.println("ID: " + id);
        System.out.println("Event: " + event);
        System.out.println("Expiration date: " + expirationDate);
        System.out.println("Reciver: " + reciver);
    }

    /**
     * Tells if the invitation is expired or not.
     *
     * @return if the invitation is expired or not.
     */
    public boolean isExpired() {
        return expirationDate.isAfter(LocalDate.now());
    }
}
