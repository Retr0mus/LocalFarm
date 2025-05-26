package com.github.countrybros.infrastructure;

import com.github.countrybros.model.event.Invitation;

import java.util.List;

public interface IInvitationRepository {

    /**
     * Adds an invitation to the repository
     *
     * @param invitation the invitation to add.
     * @return if the invitation was added or not.
     */
    public boolean addInvitation(Invitation invitation);

    /**
     * Deletes an invitation from the repository.
     *
     * @param invitationId the ID of the invitation to remove.
     * @return if the invitation was removed or not.
     */
    public boolean deleteInvitation(int invitationId);

    /**
     * Returns the required invitation.
     *
     * @param invitationId the ID of the invitation to return.
     * @return the invitation specified.
     */
    public Invitation getInvitation(int invitationId);

    /**
     * Returns all the invitations sent to a certain Company.
     *
     * @param companyId the ID of the said Company.
     * @return a list with all the invitation sent to the Company.
     */
    public List<Invitation> getInvitationsByCompany(int companyId);
}
