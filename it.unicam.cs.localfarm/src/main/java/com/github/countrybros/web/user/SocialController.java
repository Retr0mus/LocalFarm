package com.github.countrybros.web.user;

import com.github.countrybros.application.user.ISocialPublisher;
import com.github.countrybros.application.user.ISocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//TODO Those requests don't work, social API needed.
@RestController
@RequestMapping("/social")
public class SocialController {

    @Autowired
    private ISocialService socialService;

    @PostMapping("/publish-event")
    public ResponseEntity<String> publishEvent( @RequestParam int eventId) {
        //socialService.publishEvent(publisher, eventId);
        //return new ResponseEntity<>("Event published successfully", HttpStatus.OK);
        return new ResponseEntity<>("Function not available.", HttpStatus.SERVICE_UNAVAILABLE);
    }


    @PostMapping("/publish-item")
    public ResponseEntity<String> publishItemDetails( @RequestParam int productId) {
        //socialService.publishItemDetails(publisher, productId);
        //return new ResponseEntity<>("Item details published successfully", HttpStatus.OK);
        return new ResponseEntity<>("Function not available.", HttpStatus.SERVICE_UNAVAILABLE);
    }

}
