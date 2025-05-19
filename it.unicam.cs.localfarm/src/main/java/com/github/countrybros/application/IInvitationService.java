package com.github.countrybros.application;

import com.github.countrybros.model.Invitation;

import java.util.List;

/**
 * Interface that represents every possible implementation of InvitationService
 */
public interface IInvitationService {

    /**
     * Adds an invitation to the repository
     *
     * @param invitation the invitation to add.
     * @return if the invitation was added or not.
     */
    public Boolean addInvitation(Invitation invitation);

    /**
     * Removes an invitation from the repository.
     *
     * @param invitationId the ID of the invitation to remove.
     * @return if the task succeeded or not.
     */
    public Boolean deleteInvitation(int invitationId);

    /**
     * Gives the invitation with the specified ID.
     *
     * @param invitationId the specified ID.
     * @return the corresponding invitation.
     */
    public Invitation getInvitationById(int invitationId);

    /**
     * Gives all the invitations sent to a certain company.
     *
     * @param companyId the ID of the company that was invited.
     * @return a List with all the related invitations.
     */
    public List<Invitation> getInvitationsByCompany(int companyId);

    /**
     * Accepts an invitation, subscribing the said receiver into the Event's guests.
     *
     * @param invitationId the invitation to accept.
     * @return if the task succeeded or not.
     */
    public boolean acceptInvitation(int invitationId);

    /**
     * Refuses an invitation by simply deleting it.
     *
     * @param invitationId the invitation to refuse.
     * @return if the task succeeded or not.
     */
    public boolean refuseInvitation(int invitationId);
}
