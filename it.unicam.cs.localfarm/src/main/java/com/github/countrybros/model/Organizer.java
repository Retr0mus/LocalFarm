package com.github.countrybros.model;

import java.util.List;

/**
 * Class that represents an organizer of events
 */
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
