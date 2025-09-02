package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.dtos.event.InvitationDTO;
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
}
