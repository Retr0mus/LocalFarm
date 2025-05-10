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
     * Tells if the event is full of subscribers.
     *
     * @return the boolean variable representing this condition.
     */
    public boolean isFull(){
        return maxSposts >= subscribers.size();
    }

    /**
     * Subscribes a user to this event.
     *
     * @param user the user to subscribe.
     * @return if the task was successful or not.
     */
    public boolean subscribe(User user){

        if((state != EventState.currentlyPublic) || (isFull()) || (subscribers.contains(user)))
            return false;

        return subscribers.add(user);
    }

    /**
     * Unsubscribes a user from this event.
     *
     * @param user the user to unsubscribe.
     * @return if the task was successful or not.
     */
    public boolean unsubscribe(User user){
        return subscribers.remove(user);
    }

    /**
     * Adds a guest company to the event.
     *
     * @param company the company to add.
     * @return if the task was successful or not.
     */
    public boolean addGuest(Company company){
        return guests.add(company);
    }

    /**
     * Removes a guest company to the event.
     *
     * @param company the company to remove.
     * @return if the task was successful or not.
     */
    public boolean removeGuest(Company company){
        return guests.remove(company);
    }

    public void setState(EventState eventState) {
        state = eventState;
    }
}
