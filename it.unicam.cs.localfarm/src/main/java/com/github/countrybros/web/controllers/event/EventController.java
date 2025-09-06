package com.github.countrybros.web.controllers.event;

import com.github.countrybros.application.facades.Orchestrator;
import com.github.countrybros.application.mappers.EventMapper;
import com.github.countrybros.application.models.dtos.event.EventDTO;
import com.github.countrybros.application.services.event.IEventService;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.application.models.requests.event.CreateEventRequest;
import com.github.countrybros.application.models.requests.event.EditEventRequest;
import com.github.countrybros.application.models.requests.event.EventElement;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<EventDTO>> getEvents(){

        return new ResponseEntity<>(eventMapper.toDTO(eventService.getAllEvents()), HttpStatus.OK);
    }

    @PutMapping("edit")
    public ResponseEntity<Object> editEvent(@RequestBody EditEventRequest request){

        eventService.editEvent(request);
        return new ResponseEntity<>("Event modified.", HttpStatus.OK);
    }

    @PutMapping("subscribe")
    public ResponseEntity<Object> subscribeOnEvent(@PathParam("userId") int userId, @PathParam("eventId") int eventId){

        eventService.subscribeOnEvent(userId, eventId);
        return new ResponseEntity<>("Subscription successful", HttpStatus.OK);
    }

    @PutMapping("unsubscribe")
    public ResponseEntity<Object> unSubscribeOnEvent(@PathParam("userId") int userId, @PathParam("eventId") int eventId){

        eventService.unsubscribeOnEvent(userId, eventId);
        return new ResponseEntity<>("Unsubscription successful", HttpStatus.OK);
    }

    @GetMapping("publicEvents")
    public ResponseEntity<Object> getPublicEvents(){

        return new ResponseEntity<>(eventMapper.toDTO(eventService.getPublicEvents()), HttpStatus.OK);
    }

    @PutMapping("delete")
    public ResponseEntity<Object> cancelEvent(@PathParam("eventId") int eventId){

        eventService.setAsCanceled(eventId);
        return new ResponseEntity<>("Event cancelled.", HttpStatus.OK);
    }

    @PostMapping( "create")
    public ResponseEntity<Object> createEvent(@RequestBody CreateEventRequest request){

        eventService.createEvent(request);
        return new ResponseEntity<>("Event created", HttpStatus.OK);
    }

    @PutMapping("confirm")
    public ResponseEntity<Object> confirmEventPublication(@PathParam("eventID") int eventId){

        eventService.confirmEventPublication(eventId);
        return new ResponseEntity<>("Event confirmed", HttpStatus.OK);
    }

    @GetMapping("get")
    public ResponseEntity<Object> getEvent(@PathParam("eventId") int eventId){

        return new ResponseEntity<>(eventMapper.toDTO(eventService.getEvent(eventId)), HttpStatus.OK);
    }

    @GetMapping("getParticipations")
    public ResponseEntity<Object> getParticipations(@PathParam("companyId") int companyId){

        List<EventDTO> participations = eventMapper.toDTO(
                orchestrator.getParticipations(companyId));
        return new ResponseEntity<>(participations, HttpStatus.OK);
    }

    @PostMapping("cancelCompanyParticipation")
    public ResponseEntity<Object> cancelCompanyParticipation(@PathParam("companyId") int companyId,
                                                             @PathParam("eventId") int eventId){

        orchestrator.cancelCompanyParticipation(eventId, companyId);
        return new ResponseEntity<>("Event cancelled", HttpStatus.OK);
    }
}