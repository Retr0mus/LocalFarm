package com.github.countrybros.application.event;

import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.application.user.ICompanyService;
import com.github.countrybros.infrastructure.IInvitationRepository;
import com.github.countrybros.infrastructure.local.LocalInvitationRepository;
import com.github.countrybros.model.event.Event;
import com.github.countrybros.model.event.Invitation;
import com.github.countrybros.model.user.Company;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the invitations.
 *
 * TODO: remember to remove the Singleton pattern when porting to SpringBoot
 */
@Service
public class InvitationService implements IInvitationService {

    private final IInvitationRepository invitationRepository = new LocalInvitationRepository();

    private final IEventService eventService;

    private final ICompanyService companyService;

    public InvitationService(final IEventService eventService, ICompanyService companyService) {

        this.eventService = eventService;
        this.companyService = companyService;
    }

    /**
     * Adds an invitation to the repository
     *
     * @param invitation the invitation to add.
     */
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

        if (!invitationRepository.exists(invitationId))
            throw new NotFoundInRepositoryException("Invitation not found");

        invitationRepository.delete(invitationId);
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

        if (!invitationRepository.exists(invitationId))
            throw new NotFoundInRepositoryException("Invitation not found");

        return invitationRepository.get(invitationId);
    }

    /**
     * Gives all the invitations sent to a certain company.
     *
     * @param companyId the ID of the company that was invited.
     * @return a List with all the related invitations.
     */
    public List<Invitation> getInvitationsByCompany(int companyId) {

        Company company = companyService.getCompany(companyId);

        return invitationRepository.getInvitationsByCompany(companyId);
    }

    /**
     * Accepts an invitation, subscribing the said receiver into the Event's guests.
     *
     * @param invitationId the invitation to accept.
     *
     *
     */
    public void acceptInvitation(int invitationId) {

        //TODO: Remind to implement proper authorization with Spring.

        Invitation invitation = getInvitation(invitationId);
        boolean isExpired = invitation.isExpired();

        if (isExpired) {

            invitationRepository.delete(invitationId);
            throw new ImpossibleRequestException("The invitation is expired");
        }

        Event event = eventService.getEvent(invitation.getEvent().getId());
        Company company = invitation.getReciver();

        if (event.getGuests().contains(company))
            throw new RequestAlreadySatisfiedException("Invitation is already satisfied");

        event.getGuests().add(company);
        eventService.editEvent(event);

        deleteInvitation(invitationId);
    }

    /**
     * Refuses an invitation by simply deleting it.
     *
     * @param invitationId the invitation to refuse.
     */
    public void refuseInvitation(int invitationId) {

        deleteInvitation(invitationId);
    }
}
