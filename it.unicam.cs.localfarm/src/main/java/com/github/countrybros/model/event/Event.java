package com.github.countrybros.model.event;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.model.user.Postable;
import com.github.countrybros.model.user.SocialPost;
import com.github.countrybros.model.user.User;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents an event.
 */
@Entity
public class Event implements Postable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int maxSpots;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Invitation> invitations = new ArrayList<>();

    @Embedded
    private Location location;

    @ElementCollection
    private List<TimeInterval> dates;

    @ManyToOne
    private Company organizer;

    @OneToMany
    private List<User> subscribers = new ArrayList<>();

    private EventState state;


    public Event(String name, int maxSpots) {
        this.name = name;
        this.maxSpots = maxSpots;
        this.state = EventState.planning;
    }

    public Event() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<TimeInterval> getDates() {
        return dates;
    }

    public Company getOrganizer() {
        return organizer;
    }

    public int getMaxSpots() {
        return maxSpots;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }

    public EventState getState() {
        return state;
    }

    public List<Invitation> getInvitations() {
        return invitations;
    }

    public Location getLocation() {
        return location;
    }

    /**
     * Tells if the event is full of subscribers.
     *
     * @return the boolean variable representing this condition.
     */
    public boolean isFull(){
        return maxSpots >= subscribers.size();
    }

    /**
     * Subscribes a user to this event.
     *
     * @param user the user to subscribe.
     */
    public void subscribe(User user){
        subscribers.add(user);
    }

    /**
     * Unsubscribes a user from this event.
     *
     * @param user the user to unsubscribe.
     */
    public void unsubscribe(User user){
        subscribers.remove(user);
    }

    public List<Company> getGuests() {

        List<Company> guests = new ArrayList<>();

        for (Invitation invitation : invitations)
            if (invitation.isAccepted())
                guests.add(invitation.getReceiver());

        return guests;
    }

    public void setState(EventState eventState) {
        state = eventState;
    }

    public void setDates(List<TimeInterval> dates) {
        this.dates = dates;
    }

    public void setOrganizer(Company organizer) {
        this.organizer = organizer;
    }

    public void setMaxSpots(int maxSpots) {
        this.maxSpots = maxSpots;
    }

    public void setSubscribers(List<User> subscribers) {
        this.subscribers = subscribers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
    }

    /**
     * Returns the invitation of a company.
     *
     * @param guest The company.
     */
    public Invitation getGuestInvitation(Company guest){
        return invitations.stream()
                .filter(obj -> obj.getReceiver() == guest)
                .findFirst()
                .get();
    }

    //TODO
    @Override
    public SocialPost getPost() {
        return null;
    }
}
