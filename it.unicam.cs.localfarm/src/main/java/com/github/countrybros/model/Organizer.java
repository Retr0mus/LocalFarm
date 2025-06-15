package com.github.countrybros.model;

import com.github.countrybros.model.event.Event;

import java.util.List;

/**
 * Class that represents an organizer of events
 */
@Deprecated
public class Organizer {

    private int id;
    private String name;
    private List<Event> events;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Event> getEvents() {
        return events;
    }
}
