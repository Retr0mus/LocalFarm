package com.github.countrybros.application.acceptancesubmission;


import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.infrastructure.repository.IAcceptanceSubmissionRepository;
import com.github.countrybros.infrastructure.repository.IUserRepository;
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
    private IUserRepository userRepository;
    AcceptanceSubmissionFactory factory;


    public AcceptanceSubmissionService(IAcceptanceSubmissionRepository acceptanceSubmissionRepository) {
        this.acceptanceSubmissionRepository = acceptanceSubmissionRepository;
        this.factory = new AcceptanceSubmissionFactory();
    }
    /**
     * Adds an AcceptanceSubmission.
     *
     * @param request the submission to add.
     * @return if the task was successful.
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
     * @return if the task was successful or not.
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
     * @return if the task succeeded or not.
     */
    @Override
    public boolean onAcceptance(int submissionId, int curatorId) {
        AcceptanceSubmission submission = acceptanceSubmissionRepository.findById(submissionId)
                .orElseThrow(() -> new NotFoundInRepositoryException("Submission not found with id " + submissionId));

        if (submission.isAccepted()) {
            throw new RequestAlreadySatisfiedException("Submission already accepted");
        }

        userRepository.findById(curatorId)
                .orElseThrow(() -> new NotFoundInRepositoryException("Curator not found"));

        submission.setAccepted(true);
        // TODO: Remove assignation
        submission.assignCurator(curatorId);

        acceptanceSubmissionRepository.save(submission);
        return true;
    }

    /**
     * Refuse the specified AcceptanceSubmission by deleting it.
     *
     * @param submissionId the id of the AcceptanceSubmission.
     * @return if the task succeeded or not.
     */
    @Override
    public boolean onRefusal(int submissionId) {
        if (acceptanceSubmissionRepository.existsById(submissionId)) {
            acceptanceSubmissionRepository.deleteById(submissionId);
            return true;
        }
        return false;
    }
}
