package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.dtos.event.InvitationDto;
import com.github.countrybros.application.models.requests.event.CreateInvitationRequest;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.event.Invitation;

/**
 * Maps invitations.
 */
public class InvitationMapper {


    public static InvitationDto toDTO(Invitation invitation) {

        InvitationDto invitationDTO = new InvitationDto();
        invitationDTO.eventDescription = invitation.getEvent().getDescription();
        invitationDTO.eventName = invitation.getEvent().getName();
        invitationDTO.expiration = invitation.getExpiration();
        invitationDTO.location = invitation.getEvent().getLocation();
        invitationDTO.timeIntervals = invitation.getEvent().getDates();

        return invitationDTO;
    }

    public static Invitation toEntity(CreateInvitationRequest request, Company company) {
        Invitation invitation = new Invitation();
        invitation.setEvent(request.event);
        invitation.setReceiver(company);
        invitation.setExpiration(request.expiration);
        invitation.setAccepted(false);
        return invitation;
    }
}
