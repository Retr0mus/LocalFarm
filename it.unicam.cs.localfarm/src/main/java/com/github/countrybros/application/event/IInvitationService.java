package com.github.countrybros.application.event;

import com.github.countrybros.model.event.Invitation;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.application.errors.ImpossibleRequestException;

import java.util.List;

/**
 * Interface that represents every possible implementation of InvitationService
 */
public interface IInvitationService {

    /**
     * Adds an invitation to the repository
     *
     * @param invitation the invitation to add.
     */
    public void addInvitation(Invitation invitation);

    /**
     * Removes an invitation from the repository.
     *
     * @param invitationId the ID of the invitation to remove.
     *
     */
    public void deleteInvitation(int invitationId);

    /**
     * Gives the invitation with the specified ID.
     *
     * @param invitationId the specified ID.
     * @return the corresponding invitation.
     */
    public Invitation getInvitation(int invitationId);

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
     *
     * @throws RequestAlreadySatisfiedException if the invited company is already in
     * event's guest list.
     * @throws ImpossibleRequestException if the invitation is expired.
     */
    public void acceptInvitation(int invitationId);

    /**
     * Refuses an invitation by simply deleting it.
     *
     * @param invitationId the invitation to refuse.
     */
    public void refuseInvitation(int invitationId);
}
