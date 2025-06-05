package com.github.countrybros.infrastructure.web.entity;

import com.github.countrybros.model.event.EventState;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.model.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
public class EventEntity {

    //TODO: togli i commenti alle annotazioni e metti gli attributi-classi come entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    @ElementCollection
    @CollectionTable(name = "dates_for_events", joinColumns = @JoinColumn(name = ""))
    @MapKeyColumn(name = "start")
    private Map<LocalDateTime, LocalDateTime> dates;
    @Embedded
    private User organizer;
    //@OneToMany
    private List<Company> guests;
    private int maxSposts;
    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private List<User> subscribers;
    //TODO: Add Location class
    private EventState state;
}
