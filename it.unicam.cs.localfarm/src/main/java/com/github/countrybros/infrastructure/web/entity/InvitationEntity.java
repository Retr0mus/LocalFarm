package com.github.countrybros.infrastructure.web.entity;

import com.github.countrybros.model.event.Event;
import com.github.countrybros.model.user.Company;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class InvitationEntity {

    //TODO: togli i commenti alle annotazioni e metti gli attributi-classi come entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    private EventEntity event;
    private LocalDate expirationDate;
    @Embedded
    private Company reciver;

    public InvitationEntity() {}

}
