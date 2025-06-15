package com.github.countrybros.web.event;

import com.github.countrybros.application.event.IEventService;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.web.event.requests.CreateEventRequest;
import com.github.countrybros.web.event.requests.EditEventRequest;
import com.github.countrybros.web.event.requests.EventElement;
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

    @Autowired
    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value="/events")
    public ResponseEntity<List<EventElement>> getEvents(){

        return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
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
    public ResponseEntity<List<EventElement>> getPublicEvents(){

        return new ResponseEntity<>(eventService.getPublicEvents(), HttpStatus.OK);
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

    @PutMapping("cancelUserParicipation")
    public ResponseEntity<Object> cancelUserParticipation(@PathParam("eventId") int eventId
                                                    , @PathParam("userId") int userId){

        eventService.cancelCompanyParticipation(userId, eventId);
        return new ResponseEntity<>("Participation cancelled", HttpStatus.OK);
    }

    @PutMapping("confirmCompanyParticipation")
    public ResponseEntity<Object> confirmCompanyParticipation(@PathParam("eventID") int eventId
                                                            ,@PathParam("companyId") int companyId){

        eventService.confirmCompanyParticipation(eventId, companyId);
        return new ResponseEntity<>("Participation confirmed", HttpStatus.OK);
    }

    @GetMapping("get")
    public ResponseEntity<Object> getEvent(@PathParam("eventId") int eventId){

        //TODO: use a different DTO and implement mapper
        Event event = eventService.getEvent(eventId);
        EventElement dto  = new EventElement();
        dto.id = eventId;
        dto.name = event.getName();

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}