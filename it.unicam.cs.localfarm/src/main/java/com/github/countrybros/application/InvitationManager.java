package com.github.countrybros.application;

import com.github.countrybros.model.Invitation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service that performs all the tasks related to the management of the invitations.
 */
public class InvitationManager {

    private static final InvitationManager instance = new InvitationManager();
    private final Map<Integer, Invitation> invitationRepository = new HashMap<Integer, Invitation>();

    public static InvitationManager getInstance() {return instance;}

    /**
     * Gives the invitation with the specified ID.
     *
     * @param invitationId the specified ID.
     * @return the corresponding invitation.
     */
    public Invitation getInvitationById(int invitationId) {
        return invitationRepository.get(invitationId);
    }

    /**
     * Gives all the invitations sent to a certain company.
     *
     * @param companyId the ID of the company that was invited.
     * @return a List with all the related invitations.
     */
    public List<Invitation> getInvitationsByCompany(int companyId) {

        List<Invitation> invitations = new ArrayList<>();

        for (Invitation invitation : invitationRepository.values())
            if(invitation.getReciver().getId() == companyId)
                invitations.add(invitation);

        return invitations;
    }

    /**
     * Removes an invitation from the repository.
     *
     * @param invitationId the ID of the invitation to remove.
     * @return if the task succeeded or not.
     */
    public boolean deleteInvitation(int invitationId) {
        return invitationRepository.remove(invitationId) != null;
    }

    /**
     * Accepts an invitation.
     *
     * @param invitationId the invitation to accept.
     * @return if the task succeeded or not.
     */
    public boolean acceptInvitation(int invitationId) {

        //TODO: Remind to implement proper authorization with Spring.

        boolean result = getInvitationById(invitationId).onApproval();
        InvitationManager.getInstance().deleteInvitation(invitationId);

        return result;
    }

    /**
     * Refuses an invitation.
     *
     * @param invitationId the invitation to refuse.
     * @return if the task succeeded or not.
     */
    public boolean refuseInvitation(int invitationId) {

        boolean result = getInvitationById(invitationId).onRevocation();
        InvitationManager.getInstance().deleteInvitation(invitationId);

        return result;
    }

    /**
     * Prints the details of the event.
     *
     * @param invitationId the invitation to open.
     */
    public void openInvitation(int invitationId) {
        getInvitationById(invitationId).getDetails();
    }
}
