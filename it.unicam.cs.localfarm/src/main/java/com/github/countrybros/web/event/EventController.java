package com.github.countrybros.web.event;

import com.github.countrybros.application.event.IEventService;
import com.github.countrybros.model.event.Event;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "event")
public class EventController {

    private IEventService eventService;

    public EventController(IEventService eventService) {

        this.eventService = eventService;
    }

    @RequestMapping(value="/events")
    public ResponseEntity<List<Event>> getEvents(){

        return new ResponseEntity<>(eventService.getEvents(), HttpStatus.OK);
    }

    @RequestMapping(value="/edit")
    public ResponseEntity<Object> editEvent(@RequestBody Event request){

        eventService.editEvent(request);
        return new ResponseEntity<>("Event modified.", HttpStatus.OK);
    }

    @RequestMapping(value="subscribe")
    public ResponseEntity<Object> subscribeOnEvent(@PathParam("userId") int userId, @PathParam("eventId") int eventId){

        eventService.subscribeOnEvent(userId, eventId);
        return new ResponseEntity<>("Subscription successful", HttpStatus.OK);
    }

    @RequestMapping(value="unsubscribe")
    public ResponseEntity<Object> unSubscribeOnEvent(@PathParam("userId") int userId, @PathParam("eventId") int eventId){

        eventService.unsubscribeOnEvent(userId, eventId);
        return new ResponseEntity<>("Unsubscription successful", HttpStatus.OK);
    }

    @RequestMapping(value="publicEvents")
    public ResponseEntity<List<Event>> getPublicEvents(){

        return new ResponseEntity<>(eventService.getPublicEvents(), HttpStatus.OK);
    }

    @RequestMapping(value = "cancel")
    public ResponseEntity<Object> cancelEvent(@PathParam("eventId") int eventId){

        eventService.setAsCanceled(eventId);
        return new ResponseEntity<>("Event cancelled.", HttpStatus.OK);
    }

    @RequestMapping(value = "create")
    public ResponseEntity<Object> createEvent(@RequestBody CreateEventRequest request){

        eventService.createEvent(request.event, request.invitedCompanies);
        return new ResponseEntity<>("Event created", HttpStatus.OK);
    }

    @RequestMapping(value = "confirm")
    public ResponseEntity<Object> confirmEventPublication(@PathParam("eventID") int eventId){

        eventService.confirmEventPublication(eventId);
        return new ResponseEntity<>("Event confirmed", HttpStatus.OK);
    }

    @RequestMapping(value = "cancelParicipation")
    public ResponseEntity<Object> cancelParicipation(@PathParam("eventID") int eventId
                                                    ,@PathParam("userId") int userId){

        eventService.cancelCompanyParticipation(eventId, userId);
        return new ResponseEntity<>("Participation cancelled", HttpStatus.OK);
    }

    @RequestMapping(value = "confirmParticipation")
    public ResponseEntity<Object> confirmCompanyParticipation(@PathParam("eventID") int eventId
                                                            ,@PathParam("userId") int userId){

        eventService.confirmCompanyParticipation(eventId, userId);
        return new ResponseEntity<>("Participation confirmed", HttpStatus.OK);
    }
}