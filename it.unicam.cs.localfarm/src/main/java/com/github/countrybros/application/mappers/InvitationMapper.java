package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.dtos.event.InvitationDTO;
import com.github.countrybros.application.models.requests.event.CreateInvitationRequest;
import com.github.countrybros.application.services.company.ICompanyService;
import com.github.countrybros.application.services.event.IEventService;
import com.github.countrybros.application.services.event.InvitationService;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.event.Invitation;

import java.util.ArrayList;
import java.util.List;

/**
 * Maps invitations.
 */
public class InvitationMapper {

    ICompanyService companyService;
    IEventService eventService;

    public InvitationMapper(ICompanyService companyService, IEventService eventService) {

        this.companyService = companyService;
        this.eventService = eventService;
    }

    public static List<InvitationDTO> toDTO(List<Invitation> invitations) {

        List<InvitationDTO> invitationDTOs = new ArrayList<>();

        for (Invitation invitation : invitations) {

            invitationDTOs.add(toDTO(invitation));
        }

        return invitationDTOs;
    }

    public static InvitationDTO toDTO(Invitation invitation) {

        InvitationDTO invitationDTO = new InvitationDTO();
        invitationDTO.id = invitation.getId();
        invitationDTO.senderName = invitation.getEvent().getOrganizer().getName();
        invitationDTO.eventDescription = invitation.getEvent().getDescription();
        invitationDTO.eventName = invitation.getEvent().getName();
        invitationDTO.expiration = invitation.getExpiration();
        invitationDTO.location = invitation.getEvent().getLocation();
        invitationDTO.timeIntervals = invitation.getEvent().getDates();

        return invitationDTO;
    }

    public Invitation toEntity(CreateInvitationRequest request) {
        Invitation invitation = new Invitation();
        invitation.setEvent(eventService.getEvent(request.eventId));
        invitation.setReceiver(companyService.getCompany(request.receiverId));
        invitation.setExpiration(request.expiration);
        return invitation;
    }
}
