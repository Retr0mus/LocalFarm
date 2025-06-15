package com.github.countrybros.web.event;

import com.github.countrybros.application.event.IInvitationService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invitation")
public class InvitationController {

    private final IInvitationService invitationService;

    @Autowired
    public InvitationController(IInvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @GetMapping("get")
    public ResponseEntity<Object> getInvitation(@PathParam("invitationId") int invitationId) {

        return new ResponseEntity<>(invitationService.getInvitation(invitationId), HttpStatus.OK);
    }

    @GetMapping("getCompanyInvitations")
    public ResponseEntity<Object> getCompanyInvitations(@PathParam("companyId") int companyId) {

        return new ResponseEntity<>(invitationService.getInvitationsByCompany(companyId), HttpStatus.OK);
    }

    @PutMapping("accept")
    public ResponseEntity<Object> accept(@PathParam("invitationId") int invitationId) {

        invitationService.acceptInvitation(invitationId);
        return new ResponseEntity<>("Invitation accepted", HttpStatus.OK);
    }

    // TODO: Add refuse
}
