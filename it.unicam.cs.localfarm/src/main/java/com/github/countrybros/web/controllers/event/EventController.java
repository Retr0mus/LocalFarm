package com.github.countrybros.web.controllers.event;

import com.github.countrybros.application.facades.Orchestrator;
import com.github.countrybros.application.mappers.EventMapper;
import com.github.countrybros.application.models.dtos.event.EventDto;
import com.github.countrybros.application.services.event.IEventService;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.application.models.requests.event.CreateEventRequest;
import com.github.countrybros.application.models.requests.event.EventElement;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event")
public class EventController {

    private final IEventService eventService;
    private final Orchestrator orchestrator;

    @Autowired
    public EventController(IEventService eventService, Orchestrator orchestrator) {
        this.eventService = eventService;
        this.orchestrator = orchestrator;
    }

    @GetMapping(value = "/events")
    public ResponseEntity<List<EventElement>> getEvents() {

        return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
    }

    @PutMapping("/subscribe")
    public ResponseEntity<Object> subscribeToEvent(@PathParam("userId") int userId, @PathParam("eventId") int eventId) {
        orchestrator.subscribeToEvent(userId, eventId);
        return ResponseEntity.ok("User successfully subscribed to the event.");
    }

    @PutMapping("/unsubscribe")
    public ResponseEntity<Object> unSubscribeOnEvent(@PathParam("userId") int userId, @PathParam("eventId") int eventId) {

        orchestrator.unSubscribeToEvent(userId, eventId);
        return new ResponseEntity<>("Unsubscription successful", HttpStatus.OK);
    }

    @GetMapping("/publicEvents")
    public ResponseEntity<List<EventDto>> getPublicEvents() {
        List<Event> events = eventService.getPublicEvents();
        List<EventDto> eventDtos = events.stream()
                .map(EventMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(eventDtos);
    }

    @PutMapping("/delete")
    public ResponseEntity<Object> deleteEvent(@PathParam("eventId") int eventId, @PathParam("organizerId") int organizerId) {

        eventService.deleteEvent(eventId,organizerId);
        return new ResponseEntity<>("Event cancelled.", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createEvent(@Valid @RequestBody CreateEventRequest request) {

        orchestrator.createEvent(request);
        return new ResponseEntity<>("Event created successfully", HttpStatus.OK);
    }

    @PutMapping("confirm")
    public ResponseEntity<Object> confirmEventPublication(@PathParam("eventID") int eventId,
                                                          @PathParam("userId") int userId){
        eventService.confirmEventPublication(eventId, userId);
        return new ResponseEntity<>("Event confirmed", HttpStatus.OK);
    }

    @PutMapping("/cancelCompanyParticipation")
    public ResponseEntity<Object> cancelCompanyParticipation(@PathParam("eventId") int eventId
            , @PathParam("userId") int companyId) {

        eventService.cancelCompanyParticipation(companyId, eventId);
        return new ResponseEntity<>("Participation cancelled", HttpStatus.OK);
    }

    @PutMapping("/confirmCompanyParticipation")
    public ResponseEntity<Object> confirmCompanyParticipation(@PathParam("eventID") int eventId
            , @PathParam("companyId") int companyId) {

        eventService.confirmCompanyParticipation(eventId, companyId);
        return new ResponseEntity<>("Participation confirmed", HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<EventDto> getEvent(@PathParam("eventId") int eventId) {
        Event event = eventService.getEvent(eventId);
        return ResponseEntity.ok(EventMapper.toDTO(event));
    }

    @GetMapping("/subscribed")
    public ResponseEntity<List<EventDto>> getSubscribedEvents(@PathParam("userId") int userId) {
        List<Event> events = orchestrator.getEventsSubscribedByUser(userId);

        List<EventDto> eventDtos = events.stream()
                .map(EventMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(eventDtos);
    }

    @GetMapping("/organizerEvents")
    public ResponseEntity<List<EventDto>> getEventsByOrganizer(@PathParam("organizerId") int organizerId) {
        List<Event> events = orchestrator.getEventsByOrganizer(organizerId);
        List<EventDto> eventDtos = events.stream()
                .map(EventMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(eventDtos);
    }

}