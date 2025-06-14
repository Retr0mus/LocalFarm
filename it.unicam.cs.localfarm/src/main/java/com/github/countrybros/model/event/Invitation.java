package com.github.countrybros.model.event;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.countrybros.model.user.Company;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Class that represents an invitation for a certain company to
 * participate as a guest into an event.
 */
@Entity
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JsonBackReference
    private Event event;

    private LocalDate expiration;

    private boolean accepted;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company receiver;

    public Invitation() {}

    public Invitation(Event event, LocalDate expirationDate, Company reciver) {
        this.event = event;
        this.expiration = expirationDate;
        this.receiver = reciver;
    }

    public int getId() {return id;}

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setReceiver(Company receiver) {
        this.receiver = receiver;
    }

    public Company getReceiver() {
        return receiver;
    }

    /**
     * Tells if the invitation is expired or not.
     *
     * @return if the invitation is expired or not.
     */
    public boolean isExpired() {
        return expiration.isBefore(LocalDate.now());
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
