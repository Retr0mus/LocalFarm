package com.github.countrybros.application.event;

import com.github.countrybros.infrastructure.IInvitationRepository;
import com.github.countrybros.infrastructure.local.LocalInvitationRepository;
import com.github.countrybros.model.event.Invitation;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the invitations.
 *
 * TODO: remember to remove the Singleton pattern when porting to SpringBoot
 */
public class InvitationService implements IInvitationService {

    private static final InvitationService instance = new InvitationService();
    private final IInvitationRepository invitationRepository = new LocalInvitationRepository();

    public static InvitationService getInstance() {return instance;}

    /**
     * Adds an invitation to the repository
     *
     * @param invitation the invitation to add.
     * @return if the invitation was added or not.
     */
    public Boolean addInvitation(Invitation invitation) {
        return invitationRepository.addInvitation(invitation);
    }

    /**
     * Removes an invitation from the repository.
     *
     * @param invitationId the ID of the invitation to remove.
     * @return if the task succeeded or not.
     */
    public Boolean deleteInvitation(int invitationId) {
        return invitationRepository.deleteInvitation(invitationId);
    }

    /**
     * Gives the invitation with the specified ID.
     *
     * @param invitationId the specified ID.
     * @return the corresponding invitation.
     */
    public Invitation getInvitationById(int invitationId) {
        return invitationRepository.getInvitation(invitationId);
    }

    /**
     * Gives all the invitations sent to a certain company.
     *
     * @param companyId the ID of the company that was invited.
     * @return a List with all the related invitations.
     */
    public List<Invitation> getInvitationsByCompany(int companyId) {
        //TODO: this doesn't works
        return invitationRepository.getInvitationsByCompany(companyId);
    }

    /**
     * Accepts an invitation, subscribing the said receiver into the Event's guests.
     *
     * @param invitationId the invitation to accept.
     * @return if the task succeeded or not.
     */
    public boolean acceptInvitation(int invitationId) {

        //TODO: Remind to implement proper authorization with Spring.
        //TODO: manage the singleton of EventService

        Invitation invitation = getInvitationById(invitationId);
        boolean isExpired = invitation.isExpired();

        if(!isExpired)
            //EventService.getInstance().confirmCompanyPartecipation(invitation.getEvent().getId(), invitation.getReciver().getId());

        deleteInvitation(invitationId);

        return !isExpired;
    }

    /**
     * Refuses an invitation by simply deleting it.
     *
     * @param invitationId the invitation to refuse.
     * @return if the task succeeded or not.
     */
    public boolean refuseInvitation(int invitationId) {
        return deleteInvitation(invitationId);
    }
}
