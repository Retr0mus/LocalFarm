package com.github.countrybros.infrastructure.local;

import com.github.countrybros.infrastructure.IInvitationRepository;
import com.github.countrybros.model.Invitation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalInvitationRepository implements IInvitationRepository {

    private final Map<Integer, Invitation> invitationRepository = new HashMap<>();

    /**
     * Adds an invitation to the repository
     *
     * @param invitation the invitation to add.
     * @return if the invitation was added or not.
     */
    @Override
    public boolean addInvitation(Invitation invitation) {
        return invitationRepository.put(invitation.getId(), invitation) == null;
    }

    /**
     * Deletes an invitation from the repository.
     *
     * @param invitationId the ID of the invitation to remove.
     * @return if the invitation was removed or not.
     */
    @Override
    public boolean deleteInvitation(int invitationId) {
        return invitationRepository.remove(invitationId) != null;
    }

    /**
     * Returns the required invitation.
     *
     * @param invitationId the ID of the invitation to return.
     * @return the invitation specified.
     */
    @Override
    public Invitation getInvitation(int invitationId) {
        return invitationRepository.get(invitationId);
    }

    /**
     * Returns all the invitations sent to a certain Company.
     *
     * @param companyId the ID of the said Company.
     * @return a list with all the invitation sent to the Company.
     */
    @Override
    public List<Invitation> getInvitationsByCompany(int companyId) {
        List<Invitation> invitations = new ArrayList<>();

        for (Invitation invitation : invitationRepository.values())
            if(invitation.getReciver().getId() == companyId)
                invitations.add(invitation);

        return invitations;
    }
}
