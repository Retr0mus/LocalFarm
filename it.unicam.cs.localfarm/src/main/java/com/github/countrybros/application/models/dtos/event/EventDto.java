package com.github.countrybros.application.models.dtos.event;

import com.github.countrybros.application.models.dtos.user.UserDto;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.event.Location;
import com.github.countrybros.model.event.TimeInterval;
import com.github.countrybros.model.user.User;

import java.util.List;

public class EventDto {
    public int id;
    public String name;
    public int maxSpots;

    public Location location;
    public List<TimeInterval> dates;
    public UserDto organizer;
    public List<User> subscribers;
    public List<Company> partecipants;
    public String description;
    public String state;

    public EventDto(int id,
                    String name,
                    int maxSpots,
                    Location location,
                    List<TimeInterval> dates,
                    UserDto organizer,
                    List<User> subscribers,
                    List<Company> guests,
                    String state, String description) {
        this.id = id;
        this.name = name;
        this.maxSpots = maxSpots;
        this.location = location;
        this.dates = dates;
        this.organizer = organizer;
        this.subscribers = subscribers;
        this.partecipants = guests;
        this.state = state;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxSpots() {
        return maxSpots;
    }

    public void setMaxSpots(int maxSpots) {
        this.maxSpots = maxSpots;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<TimeInterval> getDates() {
        return dates;
    }

    public void setDates(List<TimeInterval> dates) {
        this.dates = dates;
    }

    public UserDto getOrganizer() {
        return organizer;
    }

    public void setOrganizer(UserDto organizer) {
        this.organizer = organizer;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<User> subscribers) {
        this.subscribers = subscribers;
    }

    public List<Company> getPartecipants() {
        return partecipants;
    }

    public void setPartecipants(List<Company> partecipants) {
        this.partecipants = partecipants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Company> getParticipants() {
        return partecipants;
    }
}
