package com.github.countrybros.application.acceptancesubmission;


import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.application.product.IItemService;
import com.github.countrybros.application.user.IUserService;
import com.github.countrybros.infrastructure.repository.IAcceptanceSubmissionRepository;
import com.github.countrybros.infrastructure.repository.IUserRepository;
import com.github.countrybros.model.acceptancesubmission.AddProductAcceptanceSubmission;
import com.github.countrybros.model.user.User;
import com.github.countrybros.web.acceptancesubmission.request.*;
import com.github.countrybros.model.acceptancesubmission.AcceptanceSubmission;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the product acceptance submissions.
 */
@Service
public class AcceptanceSubmissionService implements IAcceptanceSubmissionService {

    private IAcceptanceSubmissionRepository acceptanceSubmissionRepository;
    private IUserService userService;
    private AcceptanceSubmissionFactory factory;


    public AcceptanceSubmissionService(IAcceptanceSubmissionRepository acceptanceSubmissionRepository,
                                       IUserService userService) {
        this.acceptanceSubmissionRepository = acceptanceSubmissionRepository;
        this.factory = new AcceptanceSubmissionFactory();
        this.userService = userService;
    }
    /**
     * Adds an AcceptanceSubmission.
     *
     * @param request the submission to add.
     */
    @Override
    public void addAcceptanceSubmission(AcceptanceSubmissionRequest request) {
        AcceptanceSubmission submission = factory.create(request);
        acceptanceSubmissionRepository.save(submission);

    }


    /**
     * Deletes an AcceptanceSubmission.
     *
     * @param acceptanceSubmissionId the submission to delete.
     */
    @Override
    public void deleteAcceptanceSubmission(int acceptanceSubmissionId) {
        acceptanceSubmissionRepository.deleteById(acceptanceSubmissionId);
    }

    /**
     * Gets the required AcceptanceSubmissions.
     *
     * @param acceptanceSubmissionId the id of the wanted AcceptanceSubmission.
     * @return the said AcceptanceSubmission.
     */
    @Override
    public AcceptanceSubmission getAcceptanceSubmission(int acceptanceSubmissionId) {
        return acceptanceSubmissionRepository.findById(acceptanceSubmissionId)
                .orElseThrow(() -> new NotFoundInRepositoryException("Acceptance submission not found with id " + acceptanceSubmissionId));
    }

    /**
     * Gets all the free AcceptanceSubmissions.
     *
     * @return a list with all the said AcceptanceSubmission.
     */
    @Override
    public List<AcceptanceSubmission> getAvailableAcceptanceSubmissions() {
        return acceptanceSubmissionRepository.getAcceptanceSubmissionByAccepted(false);
    }

    /**
     * Gets all the AcceptanceSubmissions assigned to a certain Curator.
     *
     * @param curatorId the id of the User with the Curator privileges.
     * @return a list with all the curator's AcceptanceSubmission.
     */
    @Override
    public List<AcceptanceSubmission> getAcceptanceSubmissionsByCurator(int curatorId) {
//        return acceptanceSubmissionRepository.getAcceptanceSubmissionByCuratorUserId(curatorId);
        return null;
    }

    /**
     * Accepts the specified AcceptanceSubmission.
     *
     * @param submissionId the id of the AcceptanceSubmission.
     */
    @Override
    public void onAcceptance(int submissionId) {

        AcceptanceSubmission submission = getAcceptanceSubmission(submissionId);

        if (submission.isAccepted()) {
            throw new RequestAlreadySatisfiedException("Submission already accepted");
        }

        //TODO: implement
        //userService.getUser(submission.getCuratorId());

        submission.setAccepted(true);

        acceptanceSubmissionRepository.save(submission);
    }

    /**
     * Refuse the specified AcceptanceSubmission by deleting it.
     *
     * @param submissionId the id of the AcceptanceSubmission.
     */
    @Override
    public void onRefusal(int submissionId) {

        if (!acceptanceSubmissionRepository.existsById(submissionId)) {
            throw new NotFoundInRepositoryException("Submission not found with id " + submissionId);
        }

        acceptanceSubmissionRepository.deleteById(submissionId);
    }

    @Override
    public void takeChargeOfSubmission(int submissionId, int userId) {

        //check that user exists
        userService.getUser(userId);

        AcceptanceSubmission submission = getAcceptanceSubmission(submissionId);

        if (submission.isAccepted()) {
            throw new ImpossibleRequestException("Submission is already accepted");
        }

        submission.setSenderId(userId);
        acceptanceSubmissionRepository.save(submission);
    }
}
