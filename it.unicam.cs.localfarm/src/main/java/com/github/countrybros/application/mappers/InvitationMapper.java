package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.dtos.event.InvitationDto;
import com.github.countrybros.application.models.requests.event.CreateInvitationRequest;
import com.github.countrybros.application.services.company.ICompanyService;
import com.github.countrybros.application.services.event.IEventService;
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

    public static List<InvitationDto> toDTO(List<Invitation> invitations) {

        List<InvitationDto> invitationDtos = new ArrayList<>();

        for (Invitation invitation : invitations) {

            invitationDtos.add(toDTO(invitation));
        }

        return invitationDtos;
    }

    public static InvitationDto toDTO(Invitation invitation) {

        InvitationDto invitationDTO = new InvitationDto();
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
