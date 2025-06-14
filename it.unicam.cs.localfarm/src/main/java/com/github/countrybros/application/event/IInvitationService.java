package com.github.countrybros.application.event;

import com.github.countrybros.model.event.Invitation;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.web.event.requests.CreateInvitationRequest;

import java.util.List;

/**
 * Interface that represents every possible implementation of InvitationService
 */
public interface IInvitationService {

    /**
     * Adds an invitation to the repository
     *
     * @param request The request to add invitation.
     */
    public Invitation addInvitation(CreateInvitationRequest request);

    /**
     * Refuses/cancels an invitation.
     *
     * @param invitationId the ID of the invitation to cancel.
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
}
