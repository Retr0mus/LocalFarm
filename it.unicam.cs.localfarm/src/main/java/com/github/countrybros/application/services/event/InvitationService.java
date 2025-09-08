package com.github.countrybros.application.services.event;

import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.mappers.InvitationMapper;
import com.github.countrybros.application.services.company.ICompanyService;
import com.github.countrybros.infrastructure.repositories.event.IInvitationRepository;
import com.github.countrybros.model.event.Invitation;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.application.models.requests.event.CreateInvitationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the invitations.
 */
@Service
public class InvitationService implements IInvitationService {

    private final IInvitationRepository invitationRepository;

    public InvitationService(IInvitationRepository invitationRepository) {


        this.invitationRepository = invitationRepository;
    }

    @Override
    public void addInvitation(Invitation invitation) {

        invitationRepository.save(invitation);
    }

    /**
     * Removes an invitation from the repository.
     *
     * @param invitationId the ID of the invitation to remove.
     *
     * @throws NotFoundInRepositoryException if the invitation doesn't exist.
     */
    public void deleteInvitation(int invitationId) {

        Invitation invitation = getInvitation(invitationId);

        invitationRepository.deleteById(invitationId);
    }

    /**
     * Gives the invitation with the specified ID.
     *
     * @param invitationId the specified ID.
     *
     * @return the corresponding invitation.
     *
     * @throws NotFoundInRepositoryException if the invitation doesn't exist.
     */
    public Invitation getInvitation(int invitationId) {

        if (!invitationRepository.existsById(invitationId))
            throw new NotFoundInRepositoryException("Invitation not found");

        return invitationRepository.findById(invitationId).orElse(null);
    }

    /**
     * Gives all the invitations sent to a certain company.
     *
     * @param companyId the ID of the company that was invited.
     * @return a List with all the related invitations.
     */
    public List<Invitation> getInvitationsByCompany(int companyId) {

        List<Invitation> invitations = invitationRepository.findAllByReceiver_Id(companyId);

        for (Invitation invitation : invitations)
            if (invitation.isExpired()) {
                invitations.remove(invitation);
                invitationRepository.delete(invitation);
            }

        return invitations;
    }

    /**
     * Accepts an invitation, subscribing the said receiver into the Event's guests.
     *
     * @param invitationId the invitation to accept.
     *
     *
     */
    public void acceptInvitation(int invitationId) {

        Invitation invitation = getInvitation(invitationId);

        if (invitation.isExpired()) {

            invitationRepository.deleteById(invitationId);
            throw new ImpossibleRequestException("The invitation is expired");
        }

        invitationRepository.save(invitation);
    }
}
