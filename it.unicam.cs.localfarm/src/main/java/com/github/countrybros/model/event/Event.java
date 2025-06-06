package com.github.countrybros.model.event;

import com.github.countrybros.model.Organizer;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.model.user.User;
import jakarta.persistence.*;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class that represents an event.
 */
@Entity
public class Event {

    //TODO: togli i commenti alle annotazioni e metti gli attributi-classi come entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @ElementCollection
    private List<TimeInterval> dates;

    @Transient
    private User organizer;

    //@OneToMany
    @Transient
    private List<Company> guests;

    private int maxSposts;

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Transient
    private List<User> subscribers = new ArrayList<>();

    //TODO: Add Location class
    private EventState state;


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<TimeInterval> getDates() {
        return dates;
    }

    public User getOrganizer() {
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
