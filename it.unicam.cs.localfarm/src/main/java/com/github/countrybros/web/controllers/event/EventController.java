package com.github.countrybros.web.controllers.event;

import com.github.countrybros.application.facades.Orchestrator;
import com.github.countrybros.application.mappers.EventMapper;
import com.github.countrybros.application.models.dtos.event.EventDto;
import com.github.countrybros.application.services.event.IEventService;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.application.models.requests.event.CreateEventRequest;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private final IEventService eventService;
    private final Orchestrator orchestrator;
    private final EventMapper eventMapper = new EventMapper();

    @Autowired
    public EventController(IEventService eventService, Orchestrator orchestrator) {
        this.eventService = eventService;
        this.orchestrator = orchestrator;
    }

    @GetMapping(value="events")
    public ResponseEntity<List<EventDto>> getEvents(){

        return new ResponseEntity<>(eventMapper.toDTO(eventService.getAllEvents()), HttpStatus.OK);
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

    @GetMapping("publicEvents")
    public ResponseEntity<Object> getPublicEvents(){

        return new ResponseEntity<>(eventMapper.toDTO(eventService.getPublicEvents()), HttpStatus.OK);
    }

    @GetMapping("pendingEvents")
    public ResponseEntity<Object> getPendingEvents(@PathParam("userId") int userId){
        return new ResponseEntity<>(eventService.getPendingEvents(userId).stream().map(EventMapper::toDTO), HttpStatus.OK);
    }

    @PutMapping("delete")
    public ResponseEntity<Object> deleteEvent(@PathParam("eventId") int eventId, @PathParam("organizerId") int organizerId) {

        eventService.deleteEvent(eventId,organizerId);
        return new ResponseEntity<>("Event cancelled.", HttpStatus.OK);
    }

    @PostMapping("create")
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

    @GetMapping("get")
    public ResponseEntity<Object> getEvent(@PathParam("eventId") int eventId){

        return new ResponseEntity<>(eventMapper.toDTO(eventService.getEvent(eventId)), HttpStatus.OK);
    }

    @GetMapping("getParticipations")
    public ResponseEntity<Object> getParticipations(@PathParam("companyId") int companyId){

        List<EventDto> participations = eventMapper.toDTO(
                orchestrator.getParticipations(companyId));
        return new ResponseEntity<>(participations, HttpStatus.OK);
    }

    @PostMapping("cancelCompanyParticipation")
    public ResponseEntity<Object> cancelCompanyParticipation(@PathParam("companyId") int companyId,
                                                             @PathParam("eventId") int eventId){

        orchestrator.cancelCompanyParticipation(eventId, companyId);
        return new ResponseEntity<>("Event cancelled", HttpStatus.OK);
    }

    @GetMapping("/subscribed")
    public ResponseEntity<List<EventDto>> getSubscribedEvents(@PathParam("userId") int userId) {
        List<Event> events = orchestrator.getEventsSubscribedByUser(userId);

        List<Event> eventDtos = new ArrayList<>(events);

        return ResponseEntity.ok(eventMapper.toDTO(eventDtos));
    }

    @GetMapping("/organizerEvents")
    public ResponseEntity<List<EventDto>> getEventsByOrganizer(@PathParam("organizerId") int organizerId) {
        List<Event> events = orchestrator.getEventsByOrganizer(organizerId);
        List<Event> eventDtos = new ArrayList<>(events);
        return ResponseEntity.ok(eventMapper.toDTO(eventDtos));
    }
}