package com.github.countrybros.application;

import com.github.countrybros.model.Invitation;

import java.util.List;

/**
 * Service that performs all the tasks releted to the managemnent of the invitations.
 */
public class InvitationManager {

    private List<Invitation> invitationRepository;

    public List<Invitation> getInvitationsBySeller(int sellerId) {
        return null;
    }

    public boolean deleteInvitation(int invitationId) {
        return false;
    }

    public boolean acceptInvitation(int invitationId) {
        return false;
    }

    public boolean refuseInvitation(int invitationId) {
        return false;
    }

    public Invitation getInvitation(int invitationId) {
        return null;
    }
}
