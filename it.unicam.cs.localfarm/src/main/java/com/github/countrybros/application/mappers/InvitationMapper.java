package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.dtos.event.InvitationDTO;
import com.github.countrybros.application.models.requests.event.CreateInvitationRequest;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.event.Invitation;

import java.util.ArrayList;
import java.util.List;

/**
 * Maps invitations.
 */
public class InvitationMapper {

    public List<InvitationDTO> toDTO(List<Invitation> invitations) {

        List<InvitationDTO> invitationDTOs = new ArrayList<>();

        for (Invitation invitation : invitations) {

            invitationDTOs.add(toDTO(invitation));
        }

        return invitationDTOs;
    }

    public InvitationDTO toDTO(Invitation invitation) {

        InvitationDTO invitationDTO = new InvitationDTO();
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
