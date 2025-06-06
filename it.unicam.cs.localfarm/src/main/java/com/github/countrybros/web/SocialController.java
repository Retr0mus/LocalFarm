package com.github.countrybros.web;

import com.github.countrybros.application.user.ISocialPublisher;
import com.github.countrybros.application.user.ISocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/social")
public class SocialController {

    @Autowired
    private ISocialService socialService;

    @PostMapping("/publish-event")
    public void publishEvent(@RequestBody ISocialPublisher publisher,@RequestParam int eventId) {
        socialService.publishEvent(publisher, eventId);
    }

    @PostMapping("/publish-item")
    public void publishItemDetails(@RequestBody ISocialPublisher publisher,@RequestParam int productId) {
        socialService.publishItemDetails(publisher, productId);
    }

}
