package com.github.countrybros.web.controllers.event;

import com.github.countrybros.application.facades.Orchestrator;
import com.github.countrybros.application.mappers.InvitationMapper;
import com.github.countrybros.application.services.event.IInvitationService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invitation")
public class InvitationController {

    private final IInvitationService invitationService;
    private final InvitationMapper invitationMapper  = new InvitationMapper();
    private final Orchestrator orchestrator;

    @Autowired
    public InvitationController(IInvitationService invitationService, Orchestrator orchestrator) {
        this.invitationService = invitationService;
        this.orchestrator = orchestrator;
    }

    @GetMapping("get")
    public ResponseEntity<Object> getInvitation(@PathParam("invitationId") int invitationId) {

        return new ResponseEntity<>(invitationService.getInvitation(invitationId), HttpStatus.OK);
    }

    @GetMapping("getCompanyInvitations")
    public ResponseEntity<Object> getCompanyInvitations(@PathParam("companyId") int companyId) {

        return new ResponseEntity<>(invitationMapper.toDTO(invitationService
                .getInvitationsByCompany(companyId)), HttpStatus.OK);
    }

    @PutMapping("accept")
    public ResponseEntity<Object> accept(@PathParam("invitationId") int invitationId,
                                         @PathParam("accepted") boolean accepted) {

        orchestrator.acceptInvitation(invitationId, accepted);
        return new ResponseEntity<>("Invitation accepted", HttpStatus.OK);
    }

    @PutMapping("cancel_participation")
    public ResponseEntity<Object> cancel(@PathParam("companyId") int companyId,
                                         @PathParam("eventId") int eventId) {

        orchestrator.cancelCompanyParticipation(companyId, eventId);
        return new ResponseEntity<>("Invitation cancelled", HttpStatus.OK);
    }
}
