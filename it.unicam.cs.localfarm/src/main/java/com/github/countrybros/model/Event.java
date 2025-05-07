package com.github.countrybros.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Class that represents an event.
 */
public class Event {

    private int id;
    private String title;
    private Map<LocalDateTime, LocalDateTime> dates;
    private Organizer organizer;
    private List<Company> guests;
    private int maxSposts;
    private List<User> subscribers;
    //TODO: Add Location class
    private EventState state;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Map<LocalDateTime, LocalDateTime> getDates() {
        return dates;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public List<Company> getGuests() {
        return guests;
    }

    public int getMaxSposts() {
        return maxSposts;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }

    public EventState getState() {
        return state;
    }

    /**
     * Tells if the event is full of subscribers
     *
     * @return the boolean variable representing this condition
     */
    public boolean isFull(){
        return maxSposts >= subscribers.size();
    }
}
