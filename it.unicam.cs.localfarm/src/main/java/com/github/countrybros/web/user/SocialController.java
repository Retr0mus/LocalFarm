package com.github.countrybros.web.user;

import com.github.countrybros.application.user.ISocialPublisher;
import com.github.countrybros.application.user.ISocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/social")
public class SocialController {

    @Autowired
    private ISocialService socialService;

    @PostMapping("/publish-event")
    public ResponseEntity<String> publishEvent(@RequestBody ISocialPublisher publisher, @RequestParam int eventId) {
        socialService.publishEvent(publisher, eventId);
        return new ResponseEntity<>("Event published successfully", HttpStatus.OK);
    }


    @PostMapping("/publish-item")
    public ResponseEntity<String> publishItemDetails(@RequestBody ISocialPublisher publisher, @RequestParam int productId) {
        socialService.publishItemDetails(publisher, productId);
        return new ResponseEntity<>("Item details published successfully", HttpStatus.OK);
    }

}
