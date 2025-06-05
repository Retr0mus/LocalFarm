package com.github.countrybros.web;

import com.github.countrybros.application.event.IInvitationService;
import com.github.countrybros.model.event.Invitation;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/api/{invitation}")
public class InvitationController {

    IInvitationService invitationService;

    public InvitationController(IInvitationService invitationService) {

        this.invitationService = invitationService;
    }

    @RequestMapping(value = "add")
    public ResponseEntity<Object> addInvitation(@RequestBody Invitation invitation) {

        invitationService.addInvitation(invitation);
        return new ResponseEntity<>("Invitation saved", HttpStatus.OK);
    }

    @RequestMapping(value = "delete")
    public ResponseEntity<Object> deleteInvitation(@PathParam("invitationId") int invitation) {

        invitationService.deleteInvitation(invitation);
        return new ResponseEntity<>("Invitation deleted", HttpStatus.OK);
    }

    @RequestMapping(value = "get")
    public ResponseEntity<Object> getInvitation(@PathParam("invitationId") int invitation) {

        return new ResponseEntity<>(invitationService.getInvitation(invitation), HttpStatus.OK);
    }

    @RequestMapping(value = "getCompanyInvitations")
    public ResponseEntity<Object> getCompanyInvitations(@PathParam("companyId") int companyId) {

        return new ResponseEntity<>(invitationService.getInvitationsByCompany(companyId), HttpStatus.OK);
    }

    @RequestMapping(value = "accept")
    public ResponseEntity<Object> acceptInvitation(@PathParam("invitationId") int invitation) {

        invitationService.acceptInvitation(invitation);
        return new ResponseEntity<>("Invitation accepted", HttpStatus.OK);
    }

    @RequestMapping(value = "refuse")
    public ResponseEntity<Object> refuseInvitation(@PathParam("invitationId") int invitation) {

        invitationService.refuseInvitation(invitation);
        return new ResponseEntity<>("Invitation refused", HttpStatus.OK);
    }
}
