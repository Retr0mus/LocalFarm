package com.github.countrybros.application.event;

import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.application.user.ICompanyService;
import com.github.countrybros.infrastructure.web.InvitationRepository;
import com.github.countrybros.model.event.Invitation;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.web.event.requests.CreateInvitationRequest;
import com.github.countrybros.web.user.request.EditCompanyRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that performs all the tasks related to the management of the invitations.
 */
@Service
public class InvitationService implements IInvitationService {

    private final InvitationRepository invitationRepository;

    private final ICompanyService companyService;

    public InvitationService(ICompanyService companyService, InvitationRepository invitationRepository) {

        this.companyService = companyService;
        this.invitationRepository = invitationRepository;
    }

    @Override
    public Invitation addInvitation(CreateInvitationRequest request) {

        Company company = companyService.getCompany(request.receiverId);

        Invitation invitation = new Invitation();
        invitation.setEvent(request.event);
        invitation.setReceiver(company);
        invitation.setExpiration(request.expiration);

        return invitationRepository.save(invitation);
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

        return invitationRepository.getInvitationById(invitationId);
    }

    /**
     * Gives all the invitations sent to a certain company.
     *
     * @param companyId the ID of the company that was invited.
     * @return a List with all the related invitations.
     */
    public List<Invitation> getInvitationsByCompany(int companyId) {

        return invitationRepository.findAllByReceiver_Id(companyId);
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

        invitation.setAccepted(true);
        invitationRepository.save(invitation);
    }
}
