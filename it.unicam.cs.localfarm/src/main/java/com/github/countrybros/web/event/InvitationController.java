package com.github.countrybros.web.event;

import com.github.countrybros.application.event.IInvitationService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invitation")
public class InvitationController {

    IInvitationService invitationService;

    public InvitationController(IInvitationService invitationService) {

        this.invitationService = invitationService;
    }

    @GetMapping(value = "get")
    public ResponseEntity<Object> getInvitation(@PathParam("invitationId") int invitation) {

        return new ResponseEntity<>(invitationService.getInvitation(invitation), HttpStatus.OK);
    }

    @GetMapping(value = "getCompanyInvitations")
    public ResponseEntity<Object> getCompanyInvitations(@PathParam("companyId") int companyId) {

        return new ResponseEntity<>(invitationService.getInvitationsByCompany(companyId), HttpStatus.OK);
    }
}
