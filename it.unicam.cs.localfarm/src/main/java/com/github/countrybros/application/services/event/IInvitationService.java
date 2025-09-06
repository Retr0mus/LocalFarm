package com.github.countrybros.application.services.event;

import com.github.countrybros.model.event.Invitation;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.models.requests.event.CreateInvitationRequest;

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
    void addInvitation(CreateInvitationRequest request);

    /**
     * Refuses/cancels an invitation.
     *
     * @param invitationId the ID of the invitation to cancel.
     *
     */
    void deleteInvitation(int invitationId);

    /**
     * Gives the invitation with the specified ID.
     *
     * @param invitationId the specified ID.
     * @return the corresponding invitation.
     */
    Invitation getInvitation(int invitationId);

    /**
     * Gives all the invitations sent to a certain company.
     *
     * @param companyId the ID of the company that was invited.
     * @return a List with all the related invitations.
     */
    List<Invitation> getInvitationsByCompany(int companyId);

}
